class Raindrops

  HASH = {
    "3" => "Pling",
    "5" => "Plang",
    "7" => "Plong"
  }

  def self.convert(number)
    arr = []
    te = divisors_of(number)
    te.each { |n| arr << HASH[n.to_s] if HASH.has_key?(n.to_s)}
    return number.to_s if arr.empty?
    arr.join
  end

  def self.divisors_of(num)
    (1..num).select { |n| num % n == 0}
  end
end
