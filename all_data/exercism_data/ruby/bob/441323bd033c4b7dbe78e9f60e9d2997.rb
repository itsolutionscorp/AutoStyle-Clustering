class Bob
   def initialize
  
   end


   def hey(input)
      if input == input.upcase  
        'Woah, chill out!'
      elsif input.end_with?("?") 
        'Sure.'        
      else
      'Whatever.'
      end
   end
end
