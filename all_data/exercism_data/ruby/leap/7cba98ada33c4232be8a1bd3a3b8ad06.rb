class Year
  def self.leap?(year)
    @year = year
    divisible_by(4) && ( !divisible_by(100) || divisible_by(400) )
  end 

  def self.divisible_by(number)
    @year % number == 0
  end
end
