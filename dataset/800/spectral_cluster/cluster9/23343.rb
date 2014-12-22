


def combine_anagrams(words)
  array = words
  anagram = Hash.new

  array.each do |string|
    letters1=string.downcase.split(//).sort.join
     "string = #{string} letters1 = #{letters1}"
    anagram[letters1] = "#{anagram[letters1] } #{string}"
   puts letters1
  end

  final = Array.new
  x = 0
  anagram.each do |string|

     final[x] = string[1].split
     x = x+1
  end
  final
  #   <YOUR CODE HERE>
end


# input:
#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
#words = ['HeLLo', 'hello', 'hello']
#puts combine_anagrams(words).inspect


