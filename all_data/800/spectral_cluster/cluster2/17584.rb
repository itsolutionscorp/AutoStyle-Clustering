=begin
===============================================================================

Part 3: anagrams
An anagram is a word obtained by rearranging the letters of another word. For
example, "rats", "tars" and "star" are an anagram group because they are made
up of the same letters.

Given an array of strings, write a method that groups them into anagram groups
and returns the array of groups. Case doesn't matter in classifying string as
anagrams (but case should be preserved in the output), and the order of the
anagrams in the groups doesn't matter.

Example:
# input:
    ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

===============================================================================
=end

def combine_anagrams(words)
  #   <YOUR CODE HERE>
end










=begin


begin old_code()

return return_value

# working_string2 = working_string.gsub(/\p{Punct}/, '').gsub(/\W/, '')
working_string2 = working_string.gsub(/\p{Punct}/, '').gsub(/\W/, '').reverse
working_string == working_string2 # value to return
# working_string.gsub!(/\p{Punct}/, '').gsub!(/\W/, '')
# working_string.gsub!(/\p{Punct}/, '')
# working_string.gsub!(/\W/, '')

# (working_string.gsub!(/\p{Punct}/, '')).gsub!(/\W/, '')
# working_string.gsub(/\p{Punct}/, '').gsub!(/\W/, '') ok?
# working_string.gsub!(/\p{Punct}/, '').gsub(/\W/, '')

# working_string.gsub!(/\p{Punct}/, '').gsub!(/\W/, '').reverse
# working_string.(gsub!(/\p{Punct}/, '').gsub!(/\W/, '').reverse)
# (working_string.gsub!(/\p{Punct}/, '')).gsub!(/\W/, '').reverse
# ((working_string.gsub!(/\p{Punct}/, '')).gsub!(/\W/, '')).reverse

working_string2 == working_string2.reverse

# working_string == working_string.gsub(/\p{Punct}/, '').gsub(/\W/, '').reverse
working_string2 = working_string.gsub(/\p{Punct}/, '').gsub(/\W/, '').reverse
working_string == working_string2
  # and we're outta here







  # working_string == working_string.gsub!(/\p{Punct}/, '').gsub!(/\W/, '').reverse
  # working_string == (((working_string.gsub!(/\p{Punct}/, '')).gsub!(/\W/, '')).reverse)
  # working_string == (((working_string.gsub(/\p{Punct}/, '')).gsub(/\W/, '')).reverse)
  # working_string == working_string.gsub(/\p{Punct}/, '').gsub(/\W/, '').reverse


end

=end

=begin
result = "begin test"
result = palindrome("abba")
result = palindrome("ababa")
result = palindrome("aabba")
result = palindrome("abbaa")
result = palindrome("abbaaa")
result = palindrome("A man, a plan, a canal -- Panama")
result = palindrome("Madam, I'm Adam!")
result = palindrome("Abracadabra")
result = "end test"
=end