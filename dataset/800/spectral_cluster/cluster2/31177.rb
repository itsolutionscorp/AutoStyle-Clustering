# HW1 Part 3
def combine_anagrams(words)
	anagram = words.group_by { |word| word.chars.sort }.values
end

a = %w[cars for potatoes racs four scar screams scream]
combine_anagrams(a)
