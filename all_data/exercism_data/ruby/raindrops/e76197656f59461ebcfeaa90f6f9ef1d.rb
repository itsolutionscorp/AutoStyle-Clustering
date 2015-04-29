class Raindrops
  def self.convert(n)
    string = ""
    factors = prime_factors_of(n)
    string << "Pling" if factors.include?(3)
    string << "Plang" if factors.include?(5)
    string << "Plong" if factors.include?(7)
    string.empty? ? n.to_s : string
  end

  def self.prime_factors_of(n)
    return [] if n == 1
    factor = (2..n).find { |x| n % x == 0 }
    [factor] + prime_factors_of(n / factor)
  end
end
