def combine_anagrams(words)
  result = []
  words.each do |word|
    anagram_set = words.select { |possible_anagram| is_anagram?(word, possible_anagram) }
    (result << anagram_set) unless result.include?(anagram_set)
  end
  return result
end

def is_anagram?(word, possible_anagram)
  (word.downcase.split("").sort == possible_anagram.downcase.split("").sort)
end

