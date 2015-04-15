# http://ruby-doc.org/core-1.9.3/Enumerable.html

def combine_anagrams(words)
  return words.group_by { |word| word.downcase.chars.sort }.values
end



#set = ['carS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#combine_anagrams(set)
