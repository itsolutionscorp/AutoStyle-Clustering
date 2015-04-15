class Year
  def self.leap?(test_year)
    test(test_year)
  end

  def self.test(test_year)
    case
    when test_year % 4 != 0
      false
    when test_year % 100 != 0
      true
    when test_year % 400 != 0
      false
    else
      true
    end
  end
end
