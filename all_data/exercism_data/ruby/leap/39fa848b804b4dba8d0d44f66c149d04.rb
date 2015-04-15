class Fixnum
  def multiple_of?(x)
    (self % x).zero?
  end
end

class Year
  def self.leap?(year)
    year.multiple_of?(4) && (year.multiple_of?(400) || !year.multiple_of?(100))
  end
end
