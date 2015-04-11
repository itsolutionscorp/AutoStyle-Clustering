class Year
  def self.leap?(test_year)
    test_year % 400 == 0 || (test_year % 100 != 0 && test_year % 4 == 0)
  end
end
