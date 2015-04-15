#HW1-3 -- anagrams
def combine_anagrams(word_list)
	word_list.group_by { |x| x.downcase.chars.sort.join }.values
end

#TEST CASES
input = ['cars', 'for', 'Potatoes', 'racs', 'Four','scar', 'creams','scream','abc', 'cab', 'cafe', 'goo', 'face', 'a', 'A', 'Hello', 'HeLLo']
combine_anagrams(input)
