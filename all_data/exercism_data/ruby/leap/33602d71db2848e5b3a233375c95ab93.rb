class Year
  LEAP_RULES = {400 => true, 100 => false, 4 => true, 1 => false}

  def self.leap?(year)
    LEAP_RULES.each do |divisor, result|
      return result if year % divisor == 0
    end
  end
end
