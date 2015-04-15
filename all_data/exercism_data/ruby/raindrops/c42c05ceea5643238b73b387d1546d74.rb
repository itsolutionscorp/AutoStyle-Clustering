class Raindrops
  def convert(num)
    pling_plang_plong(prime_factors(num), num)
  end

  def prime_factors(num)
    return [] if num == 1
    factor = (2..num).find { |x| num % x == 0 }
    [factor] + prime_factors(num / factor)
  end

  def pling_plang_plong(factors, num)
    string = ""
    string += "Pling" if factors.include?(3)
    string += "Plang" if factors.include?(5)
    string += "Plong" if factors.include?(7)
    string == "" ? num.to_s : string
  end
end
