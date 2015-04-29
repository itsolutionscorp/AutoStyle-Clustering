class Raindrops

  PRIME_FACTORS = {
    3 => 'Pling', 
    5 => 'Plang', 
    7 => 'Plong'
  }

  def self.convert(num)
    result = ""
    divisible_factors = PRIME_FACTORS.select {|factor| num % factor == 0 }
    result = divisible_factors.collect { |factor, output| output }.join
    result.length > 0 ? result : num.to_s 
  end
end
