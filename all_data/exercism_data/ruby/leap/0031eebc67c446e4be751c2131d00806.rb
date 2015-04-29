class Year
  
  def self.leap?(year)
    leapness = (self.four?(year) && 
               (!self.century?(year) || self.exception?(year))) || 
               (self.four_century?(year))

    leapness ? "Yes, #{year} is a leap year" : "No, #{year} is not a leap year"
  end

  def self.four?(year)
    year%4 == 0
  end

  def self.century?(year)
    year%100 == 0
  end

  def self.four_century?(year)
    year%400 == 0
  end

  def self.exception?(year)
    year%100 == 0 && year%400 == 0
  end

end
