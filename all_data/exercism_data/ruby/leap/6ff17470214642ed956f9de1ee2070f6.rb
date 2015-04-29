class Year
  
  def self.leap?(year_num)
    if (year_num % 4 == 0) && (year_num % 100 != 0) || (year_num % 400 == 0)
      return true
    else
      return false
    end
  end

end
