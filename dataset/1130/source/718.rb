# input:
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]] 
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def sorted_char_array(string)
  return string.downcase.split(//).sort
end

def array_to_string(array)
  return array.reduce(:+)
end

def sort_string(string)
  return array_to_string(sorted_char_array(string))
end

def anagrams?(s1,s2)
  return sorted_char_array(s1) == sorted_char_array(s2)
end

def combine_anagrams(words)
  h = Hash.new([])
  words.each do |x|
    h[sort_string(x)]+=[x]
  end
  return h.values
end



# 
# h = Hash.new([])
# puts anagrams?("pots","stop")
# puts
# print "pots".downcase.sort
# puts
# print 'stop'.downcase.sort
# 
# input.each do |x|
#    h[sort_string(x)]+= [x]
# end
#  
# 
# input.each do |x|
#    h[sort_string(x)]+= 1
# end 