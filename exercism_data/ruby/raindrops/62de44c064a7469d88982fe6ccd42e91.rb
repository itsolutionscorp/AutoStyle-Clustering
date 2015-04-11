require_relative '../prime-factors/prime_factors'


# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
# - If the number does not contain 3, 5, or 7 as a prime factor,
#   just pass the number's digits straight through.

class Raindrops

  Mapping = { 3 => Pling, 5 => Plang, 7 => Plong }

  def convert(n)
    factors = PrimeFactors.for(n)
    Mapping.inject("") do | s, (k,v) |
      if factors.contains?(k) then s += v end
    end
    s.empty? ? n.to_s : s
  end

end
