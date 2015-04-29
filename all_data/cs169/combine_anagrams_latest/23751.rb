def combine_anagrams(words)
#
answer_hash = Hash.new([]);
words.each {|word| answer_hash[word.downcase.chars.sort] = answer_hash[word.downcase.chars.sort] + [word]}
answer_hash.values
end