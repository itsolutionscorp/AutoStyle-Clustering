class Raindrops
  RULES = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong",
  }

  def self.convert(number)
    prime_digits = prime_factors(number)
    return number.to_s if prime_digits.empty?

    prime_digits.map {|_digit|
      RULES[_digit]
    }.join
  end

  private
  def self.prime_factors(number)
    factors = []
    divider = factor(number)

    while !divider.nil?
      factors << divider
      number = numbe / divider
      divider = factor(number)
    end

    factors.keep_if {|_digit|
      [3, 5, 7].include?(_digit)
    }.uniq
  end

  def self.factor(number)
    (2..number).detect do |_divider|
      number % _divider == 0
    end
  end
end
