class Raindrops
  def self.convert(type)
    output = ""
    if type == 1
      output += "1"
    end
    if prime_factors(type).include?(3)
      output += "Pling"
    end
    if prime_factors(type).include?(5)
      output += "Plang"
    end
    if prime_factors(type).include?(7)
      output += "Plong"
    end
    output += "#{type}" if output.empty?
    output
  end

  def self.prime_factors(num)
    factors = []
    i = 2
    while i <= num
      factors << i if num % i == 0 && prime?(i)
      i += 1
    end
    factors
  end

  def self.prime?(num)
    i = 2
    while i < num / 2
      false if num % i == 0
      i += 1
    end
    true
  end
end
