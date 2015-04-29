class Hamming  
     def self.compute (first, second)     
       # Split strings into arrays of characters to be able to iterate through each character
       first = first.scan /\w/
       second = second.scan /\w/

       # First try at solving       
#       if first.length > second.length
#         first = first.take(second.length)
#       else
#         second = second.take(first.length)
#       end
       
#       result = first - second
#       result.length

#        first.each { |x| 
#          puts x 
#          puts second.at(x)
#        }

        # Second try
        # 
        difference = 0
        
        # Loop through first array/string and check against second array/string for a match character
        first.each_with_index {|val, index|           
          if val != second[index]
            # Make sure the element exists in the second array
            if !second[index].nil?
              difference = difference + 1
            end
          end          
        }
        
        difference
       
     end
end

#Hamming.compute('AGG', 'AAAACTGACCCACCCCAGG')
