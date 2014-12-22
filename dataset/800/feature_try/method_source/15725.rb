def combine_anagrams(words)
  anagrams = Hash.new([])
  for i in (0..(words.length - 1)) do
    (anagram_found = false
    anagrams[words[i]] = [] if (i == 0)
    for j in (0..(i - 1)) do
      (anagram1 = merge_sort(words[i].downcase)
      anagram2 = merge_sort(words[j].downcase)
      if (merge_sort(words[i].downcase) == merge_sort(words[j].downcase)) then
        (anagrams[words[j]] << words[i])
        anagram_found = true
        break
      end)
    end
    anagrams[words[i]] = [] unless anagram_found)
  end
  anagrams_arr = hash_to_array(anagrams)
  return anagrams_arr
end