class Raindrops
  def self.factor_recursion(x)
    if x == 1 
      return []
    else
      factor = (2..x).find {|i| x % i == 0} 
      return [factor] + factor_recursion(x / factor) 
    end
  end

  def self.convert(x)
    prime_factors_array = factor_recursion(x)
    output = ""
    if prime_factors_array.include? 3
      output += "Pling"
    end
    if prime_factors_array.include? 5
      output += "Plang"
    end
    if prime_factors_array.include? 7
      output += "Plong"
    end
    if !(prime_factors_array.include? 3) && !(prime_factors_array.include? 5) && !(prime_factors_array.include? 7)
      return x.to_s
    end
    return output
  end
end
