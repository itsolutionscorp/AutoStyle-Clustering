def compute(first, second)

      	String.try_convert(first)    #Convert variables in Strings
      	String.try_convert(second)  

      	count=0 #counter for found differences

      	first.each_char do |i| #goes through the first string and compares each character with the same position character of the second string
      		if i!=second[0] ##compares the char of the first string with the first char of the second string
      			count=count+1 #if the chars are different add 1 to the counter variable
      		end
      		second = second[1..-1]	#after each loop delete the last carachter of the second string so that the character positions are in line
      	end
      	
      	return count #return the amaout of found differences 




      	
      end