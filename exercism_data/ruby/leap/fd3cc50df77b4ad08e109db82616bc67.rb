class Year  
  def self.leap?(year)
    result = true
    if year % 4 > 0
      result = false
    else
      if year % 100 == 0
        if year % 400 > 0
          result = false
        end
      end
    end
    result
  end
end
