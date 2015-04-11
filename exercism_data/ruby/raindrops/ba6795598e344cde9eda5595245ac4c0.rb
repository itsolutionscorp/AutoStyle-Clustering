require 'prime'

class Raindrops
  def self.convert(num)
    pf = prime_factors num
    res = ""
    res += "Pling" if pf.include? 3
    res += "Plang" if pf.include? 5
    res += "Plong" if pf.include? 7
    res == "" ? num.to_s : res
  end

  def self.prime_factors(n)
    Prime.prime_division(n).flat_map { |f, p| [f] * p }
  end
end
