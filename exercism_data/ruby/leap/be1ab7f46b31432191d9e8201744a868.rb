class Year
  def self.leap?(test_year)
    return test_year % 400 == 0  if test_year % 100 == 0 
    test_year % 4 == 0
  end
end
