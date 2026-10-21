#include <iostream>
#include <string>
#include <curl/curl.h>
#include <algorithm>
std::string response_string;
static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t totalSize = size * nmemb;
    response_string.append((char*)contents, totalSize);
    return totalSize;
}
int main(int argc, char* argv[]) {
    if (argc < 3) return 1;
    std::string fullUrl = std::string(argv[1]) + argv[2];
    CURL* curl; CURLcode res;
    curl_global_init(CURL_GLOBAL_ALL);
    curl = curl_easy_init();
    if(curl) {
        curl_easy_setopt(curl, CURLOPT_URL, fullUrl.c_str());
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
        curl_easy_setopt(curl, CURLOPT_TIMEOUT, 10L);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback); 
        res = curl_easy_perform(curl);
        if(res == CURLE_OK) {
            long http_code = 0;
            curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &http_code);
            std::cout << "STATUS:" << http_code << std::endl;
            std::cout << "BODY_START" << std::endl;
            response_string.erase(std::remove(response_string.begin(), response_string.end(), '\0'), response_string.end());
            response_string.erase(std::remove(response_string.begin(), response_string.end(), '\r'), response_string.end());
            std::cout << response_string << std::endl;
            std::cout << "BODY_END" << std::endl << std::flush;
        } else {
            std::cout << "STATUS:ERROR" << std::endl << "BODY_START" << std::endl << "ERROR" << std::endl << "BODY_END" << std::endl << std::flush;
        }
        curl_easy_cleanup(curl);
    }
    curl_global_cleanup();
    return 0;
}
