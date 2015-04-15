class Year

  def self.leap?(year)
    if (year % 400 == 0) || ((year % 4 == 0 ) && (year % 100 != 0))
      'Yes, #{year} is a leap year'
    else
      false
    end
  end
end
