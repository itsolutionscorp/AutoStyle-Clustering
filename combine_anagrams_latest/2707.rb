def combine_anagrams(words)
  anagram_list = Hash.new
  words.each do |word|
    sword = word.downcase.chars.sort.join
    if anagram_list[sword]
      anagram_list[sword] = anagram_list[sword] << word
    else
      anagram_list[sword] = [word]
    end
  end
  res = []
  anagram_list.each_pair do |key,alist|
    res = res + [alist]
  end
  return res
end
