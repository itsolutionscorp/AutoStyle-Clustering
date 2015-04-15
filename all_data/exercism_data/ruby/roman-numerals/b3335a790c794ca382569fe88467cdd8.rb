	
class Fixnum
  FACTORS = [['m', 1000], ['cm', 900], ['d', 500], ['cd', 400],
  		   ['c', 100], ['xc', 90], ['l', 50], ['xl', 40], 
  		   ['x', 10], ['ix', 9], ['v', 5], ['iv', 4], ['i', 1]]

  def to_roman
    value = self
  	if value < 1 || value > 3000
  		return "Out of range"
  	else
      roman_numeral = ""
      for letter, number in FACTORS
      	count, value = value.divmod(number)
      	roman_numeral << letter * count
      end
    end
    roman_numeral.upcase
  end
end
