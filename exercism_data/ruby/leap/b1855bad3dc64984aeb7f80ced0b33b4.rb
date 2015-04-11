# overwriting Fixnum
class Fixnum
  def divisible_by?(n)
    self % n == 0
  end
end

# Year class
class Year
  def self.leap?(year)
    (year.divisible_by?(4) && !year.divisible_by?(100)) \
    || \
    year.divisible_by?(400)
  end
end
