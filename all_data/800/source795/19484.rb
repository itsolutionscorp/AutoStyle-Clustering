=begin
An anagram is a word obtained by rearranging the letters of another word.  For
example, "rats", "tars" and "star" are an anagram group because they are made up of the same
letters.
Given an array of strings, write a method that groups them into anagram groups and returns
the array of groups.  Case doesn't matter in classifying string as anagrams (but case should be
preserved in the output), and the order of the anagrams in the groups doesn't matter.
Example:
# input:

#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
=end
def combine_anagrams(words)
  rslt = Array.new
  words.each  do |word|
    ind = -1
    i = 0
    rslt.each do |arr|
      if (arr[0].downcase.chars().sort() == word.downcase.chars().sort()) then
        ind = i
      end
      i = i + 1
    end
    unless ind == -1
      rslt[ind] << word
    else
      rslt[rslt.size] = Array.new
      rslt[rslt.size-1] << word
    end
  end
  return rslt
end