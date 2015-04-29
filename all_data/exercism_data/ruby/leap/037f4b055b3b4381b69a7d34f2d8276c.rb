class Fixnum
  def divisible_by(other)
    self % other == 0
  end
  def not_divisible_by(other)
    !divisible_by(other)
  end
end
module Year
  def self.leap? year
    year.divisible_by(4) and (year.not_divisible_by 100 or year.divisible_by 400)
  end
end
