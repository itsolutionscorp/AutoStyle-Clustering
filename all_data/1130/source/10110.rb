# 3. Given an array of strings, write a method that groups them into 
# anagram groups and returns the array of groups

def combine_anagrams(words)
	hash = Hash.new{|k, v| hash[v] = []}
	words.each{|word| hash[word.downcase.chars.sort.join] << word}
	return hash.values
end