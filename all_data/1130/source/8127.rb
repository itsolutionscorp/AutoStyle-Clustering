
# def combine_even_odd(numbers)
#   
  # return numbers.group_by {|num| num.sort}.values
#   
# end

def combine_anagrams(words)
# <YOUR CODE HERE>

return words.group_by { |word| word.downcase.chars.sort }.values

# res={}
# 
# words.each do |word|
  # key=word.split('').sort.join
  # res[key] ||= []
  # res[key] << word
# end
# 
# return res.values

# puts words.length
# tempSort = words.sort
# 
# temp = Array.new
# 
# tempSort.each do |word|
  # temp.push(word.downcase.chars.sort.join)
# end
# 
# puts temp.sort
# 
# temp = words
# 
# h = Hash.new
# 
# words.each do |word|
  # h
# end

# h = Hash.new
# 
# words.each do |word|
  # h[word] = word
  # # puts temp
# end
# 
# puts h
# 
# words.each do |word|
  # temp = word.downcase.chars.sort.join
  # if(h[temp] == temp)
    # h[word] += word
  # end
  # # puts temp
# end

end

# p combine_even_odd(%w(1 2 3 4 7 8 6))

# input:
a1 = ['caRs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
p combine_anagrams(a1)

