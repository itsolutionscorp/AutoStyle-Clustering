class Numeric
  # an array of the roman numerals, in order of their value. 
  @@roman_nvmerals = %w[I V X L C D M]
  
  def to_roman
    #take the number given, and divide it up into discrete digits in an array
    digits = self.to_s.scan(/./).map{|number| number.to_i}
    #a single-digit arabic number gets an index of 1, corresponding to "V"
    #in the roman_numeral array above. two-digit indexes to "L", three to "D"
    
    index = (digits.length * 2) - 1
    @@output = String.new
    
    
    digits.each{|digit| #iterate through the digits
      
      nvmerals = String.new
      
      #the case method returns a value that gets concatenated to the output.
      @@output << case digit
      when 0
        ""
      when 1..3
        #concatenate the appropriate letters the right number of times
        digit.times{nvmerals << @@roman_nvmerals[index - 1]}
        "#{nvmerals}"
        #evaluate the expression you want returned
      when  4
         nvmerals = "#{@@roman_nvmerals[index - 1]}" + "#{nvmerals << @@roman_nvmerals[index]}"
         "#{nvmerals}"
         
      when 5..8
        remainder = digit - 5
        nvmerals << @@roman_nvmerals[index]
        remainder.times{nvmerals << (@@roman_nvmerals[index - 1])}
        "#{nvmerals}"
        
      when 9
        nvmerals = "#{@@roman_nvmerals[index - 1]}" + "#{nvmerals << @@roman_nvmerals[index + 1]}"
        "#{nvmerals}"
      end

     index -= 2
    }
    return @@output.to_s
  end
end
    
