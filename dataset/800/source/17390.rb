
#To find the possible anagrams
def combine_anagrams(words)
  hashedval = Hash.new
  temparr = Array.new
  words.each do |value|
	newvalue = value.chars.sort.join.upcase
	temparr = hashedval[newvalue]
	if temparr == nil
	   temparr = Array.new
	end 
	temparr << value
	hashedval[newvalue] = temparr
  end
  return hashedval
end

#Iterating through the anagram hash
def display_result(inputhash)
    inputhash.each_pair do |key,value|
	   puts value.inspect
	end
end

#Input anagram array
input = ['rats','star','sart','lofr','forl','he','EH']

#Calling the method to find anagrams and display result
display_result(combine_anagrams(input))