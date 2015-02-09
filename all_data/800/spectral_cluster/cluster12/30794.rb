#!/usr/bin/env ruby
class IncorrectInputError < StandardError; end

test1 = [['pots', 'spot', 'stop', 'tops', 'tops'], ['spots', 'stops'], ['sausage']]
test2 =['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
test3 = [['a', 'a'], ['b', 'b'], ['c'], ['d']]
test4 = [['cars', 'cars', 'racs', 'scar'], ['four'], ['for'], ['potatoes'], ['creams', 'scream', 'scream']]
test5 = ['HeLLo', 'hello']
test6 =[]
test7 = ['A', 'a']
test8 = ['HeLLo', 'hello']

def combine_anagrams(words)
  if words == []; return words; end

  if words[0].kind_of? String
    anagrams = words.group_by do |word| 
      word.downcase.chars.sort
    end.values
  elsif words[0].kind_of? Array
    anagrams = words.group_by { |word| word.sort }.values
  else
    raise IncorrectInputError
  end
  return anagrams
end 

#def testGroup(words)
# p arr = words.group_by {|x| x.downcase } ; gets
#end

#testGroup(e)










#=begin

puts "---------------------------------------------------"
p combine_anagrams(test1)
puts "---------------------------------------------------"
p combine_anagrams(test2)
puts "---------------------------------------------------"
p combine_anagrams(test3)
puts "---------------------------------------------------"
p combine_anagrams(test4)
puts "---------------------------------------------------"
p combine_anagrams(test5)
puts "---------------------------------------------------"
p combine_anagrams(test6)
puts "---------------------------------------------------"
p combine_anagrams(test7)
puts "---------------------------------------------------"
p combine_anagrams(test8)
puts "---------------------------------------------------"
#p combine_anagrams(test9)

#=end
