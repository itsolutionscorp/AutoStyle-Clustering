class Fixnum

	def to_roman
	 	result = String.new
		number = self
		roman_mapping.keys.each do |k|
			quotient,dividend = number.divmod(k)
			result << roman_mapping[k] * quotient
			number = dividend
			end
		result    
	end


private
 
  def roman_mapping
    {
      1000 => "M",
      900 => "CM",
      500 => "D",
      400 => "CD",
      100 => "C",
      90 => "XC",
      50 => "L",
      40 => "XL",
      10 => "X",
      9 => "IX",
      5 => "V",
      4 => "IV",
      1 => "I"
    }
  end
	
end

#p 4.to_roman
#p 0.to_roman
#p 3.to_roman
575.to_roman
