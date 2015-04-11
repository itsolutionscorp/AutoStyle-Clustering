class Year

  def self.leap?(year)
    if year%4 == 0
      check_hundred(year)
    end
  end

  def self.check_hundred(year)
    if year%100 == 0
      check_four_hundred(year)
    else
      "Yes, #{year} is a leap year"
    end
  end

  def self.check_four_hundred(year)
    if year%400 == 0
      "Yes, #{year} is a leap year"
    end
  end
end
