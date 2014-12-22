def combine_anagrams(words)
  result =[]
  words.each do |word|
    anagram_set = words.select do |possible_anagram|
      is_anagram?(word, possible_anagram)
    end
    result << anagram_set unless result.include? anagram_set
  end
  return result
end

def is_anagram?(word, possible_anagram)
  word.downcase.split('').sort == possible_anagram.downcase.split('').sort
end