class Raindrops

  FACTORS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    result = divisors_of(number).map { |n| FACTORS.fetch(n) if FACTORS.has_key?(n) }.compact
    result.empty?? number.to_s : result.join
  end

  def self.divisors_of(num)
    (1..num).select { |n| num % n == 0}
  end
end
