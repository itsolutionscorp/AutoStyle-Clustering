# ['caRS', 'for', 'potatoes', 'raCs', 'four','sCaR', 'creams', 'scream']
def combine_anagrams(words)
	words.group_by { |word| word.downcase.split("").sort.join }.values
end