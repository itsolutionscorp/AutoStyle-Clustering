def combine_anagrams(words)
  hash = {}
  words.each do |word|
    sort_word = word.downcase.chars.sort.join
    hash[sort_word] ||= []
    hash[sort_word] << word
  end
  return hash.values
end