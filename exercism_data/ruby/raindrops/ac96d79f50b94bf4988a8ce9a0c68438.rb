class Raindrops
  def self.convert(num)
    final = ""
    prime_factors = (1..num).select { |n| num % n == 0}
    if prime_factors.include?(3)
      final += "Pling"
    end
    if prime_factors.include?(5)
      final += "Plang"
    end
    if prime_factors.include?(7)
      final += "Plong"
    end
    if final.empty?
      return num.to_s
    else
      return final
    end
  end
end
