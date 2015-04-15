class Year

  def self.leap?(year)
    condition_1 = year % 4 == 0
    condition_2 = (year % 100 != 0) || year % 100 == 0 && year % 400 == 0

    condition_1 ? true : false
    condition_1 && condition_2 ? true : false
  end

end
