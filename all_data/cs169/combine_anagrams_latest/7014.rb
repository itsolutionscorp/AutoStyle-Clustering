# Michael McGrath
# HW1-3

def combine_anagrams(words)
  hash = Hash.new(Array.new)
  words.each {|word| hash[word.downcase.chars.sort.join] += [word]}
	hash.values
end