def detect_anagrams(str1, str2):
    str1_list = list(str1)
    str1_list.sort()
    for i in str2:
    	str2_list =  list(i)
    	str2_list.sort()
    	if str1_list == str2_list:
    		return i
#detect_anagrams('ant', 'tan stand at'.split())
