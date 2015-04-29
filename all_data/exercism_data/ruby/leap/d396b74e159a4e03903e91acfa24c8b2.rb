require 'date'

class Year

  def self.leap?(year)
    if (year % 4 == 0) && (year % 400 == 0)
      true
    elsif year % 4 == 0 && year % 100 != 0
      true
    elsif  (year % 400 != 0)
      false
    else 
      false
    end      
  end


end
