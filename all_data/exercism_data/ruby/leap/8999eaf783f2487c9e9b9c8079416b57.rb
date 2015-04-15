class Year

  def self.leap?(year)
    if year % 4 == 0 
      if year % 100 == 0 && year % 400 == 0
        return true
      elsif year % 100 == 0
        return false
      else
        return true
      end
    else
      return false
    end
    
  end

end
