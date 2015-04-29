class Integer
  def multiple_of?(num)
    self % num == 0
  end
end

class Year
  def self.leap?(year)
    year.multiple_of?(400) || (
      year.multiple_of?(4) && !year.multiple_of?(100)
    )
  end
end
