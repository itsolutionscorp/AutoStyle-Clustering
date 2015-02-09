def combine_anagrams(words)
  str_after_sort_dict = {}
  anagram_list = []
  
  words.each do |word|
    sorted_word = (word.downcase).chars.sort.join
    if str_after_sort_dict[sorted_word] == nil
      str_after_sort_dict[sorted_word] = word
    else
      str_after_sort_dict[sorted_word] += "@"
      str_after_sort_dict[sorted_word] += word
    end
  end
  
  str_after_sort_dict.each do |key, value|
    splitStr = value.split("@")
    anagram_list += [splitStr]
  end
  
  return anagram_list
end
