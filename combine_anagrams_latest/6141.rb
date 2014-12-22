# input:
require "pp"

#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)

       ouArr = Array.new

       words.each do |w|

             ouIt = Array.new [w]

             words.each do |w2|
                   if  w.downcase.chars.sort == w2.downcase.chars.sort && !ouIt.include?(w2)
                     ouIt.push(w2)
                   end
             end
             ouIt.sort!

            if !ouArr.include?(ouIt)
             ouArr.push(ouIt)
            end
       end
       ouArr
end

x = Array.new

x = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
x = ['Cars','Racs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

pp combine_anagrams x

