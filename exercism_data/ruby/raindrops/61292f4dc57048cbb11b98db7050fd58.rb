class Raindrops

  Sound_for = {
    "3" => 'Pling',
    "5" => 'Plang',
    "7" => 'Plong'
  }

  def self.convert(num)
    factors = prime_factors(num).collect{ |e| e.to_s }
    if (factors & Sound_for.keys).size > 0
      factors.collect{ |factor| Sound_for[factor] }.compact.join("")
    else
      num.to_s
    end
  end

  def self.prime_factors(num, factors=[])
    factor = (2..num).find{|divisor| num % divisor == 0} || "nil"
    if factor != "nil"
      factors << factor
      prime_factors(num/factor, factors)
    end
    factors.uniq
  end
end

# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
