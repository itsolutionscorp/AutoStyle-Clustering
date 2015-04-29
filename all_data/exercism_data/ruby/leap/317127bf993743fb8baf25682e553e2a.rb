class Year
  def self.regular_leap?(year)
    year % 4 == 0
  end

  def self.not_century?(year)
    year % 100 != 0
  end

  def self.fourth_century?(year)
    year % 400 == 0
  end

  def self.leap?(year)
    (regular_leap?(year) && not_century?(year)) || (fourth_century?(year))
  end
end
