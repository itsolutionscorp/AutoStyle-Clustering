class Year
  def regular_leap?(year)
    year % 4 == 0
  end

  def not_century?(year)
    year % 100 != 0
  end

  def fourth_century?(year)
    year % 400 == 0
  end

  def self.leap?(year)
    class_method = self.new
    (class_method.regular_leap?(year) && class_method.not_century?(year)) || (class_method.fourth_century?(year))
  end
end
