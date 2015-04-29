# anagram generator


def combine_anagrams(words)
	anagrams = words.group_by { |word| word.chars.sort }.values
	p anagrams
end


# tests

combine_anagrams(%w[cars for potatoes racs optatotes four ourf scar creams screams])
