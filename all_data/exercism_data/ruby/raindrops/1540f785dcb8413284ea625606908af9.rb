require 'prime'

class Raindrops

  def self.convert(number)
   return_string = String.new
   return_string += "Pling" if number % 3 == 0
   return_string += "Plang" if number % 5 == 0
   return_string += "Plong" if number % 7 == 0
   return_string = number.to_s if return_string.empty?
   return return_string
  end

end
