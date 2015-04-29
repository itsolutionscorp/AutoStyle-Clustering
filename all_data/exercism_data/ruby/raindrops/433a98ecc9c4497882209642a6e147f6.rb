class Raindrops
  CONVERSIONS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(x)
    divisors = CONVERSIONS.keys.select { |k| x % k == 0 }
    return x.to_s if divisors.empty?
    divisors.map { |d| CONVERSIONS[d] }.join
  end
end
