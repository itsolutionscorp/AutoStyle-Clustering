class Raindrops

  PRIMES = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    factors = PRIMES.select { |prime, text| divisible_by?(number, prime) }
    result = factors.values.reduce(&:+)

    result.nil? ? number.to_s : result
  end

  private

  def self.divisible_by?(number, factor)
    number % factor == 0
  end

end
