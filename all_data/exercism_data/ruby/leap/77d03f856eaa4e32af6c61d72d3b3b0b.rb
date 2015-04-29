class Year
  def self.leap?(year)
    if Year.divisible_by_four?(year) && Year.not_century?(year) || Year.leap_century?(year)
      true 
    else
      false
    end
  end

  def self.leap_century?(year)
    Year.divisibile?(year, 400)
  end

  def self.not_century?(year)
    !Year.divisibile?(year, 100)
  end

  def self.divisible_by_four?(year)
    Year.divisibile?(year, 4)
  end

  def self.divisibile?(year, number)
    year % number == 0
  end
end
