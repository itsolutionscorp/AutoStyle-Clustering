def combine_anagrams(words)
  anagram_groups = {}
  words.each {
    |word|
    w = []
    word.downcase().split('').each {
      |letter|
      w += [letter]
    }
    sorted_word = w.sort().join()
    if anagram_groups[sorted_word]
      anagram_groups[sorted_word] += [word]
    else
      anagram_groups[sorted_word] = [word]
    end
  }
  anagram_list = []
  anagram_groups.each_entry {
    |key, anagram_words|
    anagram_list += [anagram_words]
  }
  return anagram_list
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]