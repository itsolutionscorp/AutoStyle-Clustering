require_relative '../prime-factors/prime_factors'


# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
# - If the number does not contain 3, 5, or 7 as a prime factor,
#   just pass the number's digits straight through.

class Raindrops

  def convert(n)
    factors = PrimeFactors.for(n)
    s = ""
    if factors.member?(3) then
      s += "Pling"
    end
    if factors.member?(5) then
      s += "Plang"
    end
    if factors.member?(7) then
      s += "Plong"
    end
    if s == ""
      s = n.to_s
    end
    s
  end

end
