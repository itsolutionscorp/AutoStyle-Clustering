require 'date'

class Year

  def self.leap?(year)
    if year % 400 == 0
      true
    elsif year % 4 == 0 && year % 100 != 0
      true
    else 
      false
    end      
  end


end
