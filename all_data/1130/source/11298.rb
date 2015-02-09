def combine_anagrams(words)
# <YOUR CODE HERE>
anagrams = words.group_by { |word| word.downcase.chars.sort}.values
end

t = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
