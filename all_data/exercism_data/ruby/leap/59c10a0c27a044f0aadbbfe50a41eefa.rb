class Fixnum
  def divisible_by?(*divisors)
    divisors.all? { |divisor| self.modulo(divisor).zero? }
  end
end

class Year
  def self.leap?(year)
    year.divisible_by?(4) && !year.divisible_by?(100) || year.divisible_by?(400)
  end
end
