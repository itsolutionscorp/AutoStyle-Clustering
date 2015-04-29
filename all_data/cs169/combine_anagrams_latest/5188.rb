# Tim Chopoorian
# tim@chopoorian.com
#
# Saas Class
# Homework 1
# Part 3



def combine_anagrams(words)
 output = Array.new
 while words.length != 0
   group = Array.new
   word = words[0]
   words.each do |anagram|
     if word.downcase.chars.sort.join == anagram.downcase.chars.sort.join
       group.push(anagram)
     end
   end
   output.push(group)
   group.each { |w| words.delete(w) }
 end
 output
end

# words = ['Cars', 'for', 'For', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# 
# 
# puts combine_anagrams(words).inspect


