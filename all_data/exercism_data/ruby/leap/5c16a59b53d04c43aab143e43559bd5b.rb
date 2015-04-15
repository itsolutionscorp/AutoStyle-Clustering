class Year
  # leap years occur when the year is divisible by 4
  #  but not when the year is divisible by 100
  #   unless it is also divisible by 400
  def self.leap?(y)
    y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)
  end
end
