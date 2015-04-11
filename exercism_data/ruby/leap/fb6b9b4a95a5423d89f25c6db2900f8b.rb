=begin
  File: year.rb
  Author: sherinom
=end

class Year
  
  def self.leap? (year)
    year % 4 == 0 && (!(year % 100 == 0) || year % 400 == 0)
  end
  
end

class YearTest < MiniTest::Unit::TestCase
  def test_leap_year
    assert Year.leap?(1996)
  end

  def test_non_leap_year
    skip
    refute Year.leap?(1997)
  end
  
  def test_non_leap_even_year
    skip
    refute Year.leap?(1998)
  end

  def test_century
    skip
    refute Year.leap?(1900)
  end

  def test_fourth_century
    skip
    assert Year.leap?(2400)
  end
end
