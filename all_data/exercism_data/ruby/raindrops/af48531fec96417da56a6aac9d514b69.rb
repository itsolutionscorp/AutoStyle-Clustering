class Raindrops

  HASH = {
    "3" => "Pling",
    "5" => "Plang",
    "7" => "Plong"
  }

  def self.convert(number)
    result = []
    divisors = divisors_of(number)
    divisors.each { |n| result << HASH[n.to_s] if HASH.has_key?(n.to_s)}
    return number.to_s if result.empty?
    result.join
  end

  def self.divisors_of(num)
    (1..num).select { |n| num % n == 0}
  end
end
