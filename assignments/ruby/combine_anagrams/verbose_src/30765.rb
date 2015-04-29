def is_anagram?(word1, word2)
  word1.downcase.chars.sort.eql?(word2.downcase.chars.sort)
end

def combine_anagrams(words)
    words.group_by { |w| w.downcase.chars.sort.to_s }.values
end

words =  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(words).inspect
puts 'expected [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]'
