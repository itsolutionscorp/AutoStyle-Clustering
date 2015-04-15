class Raindrops
  def self.convert(num)
    factors = FactorFactory.find_each_factor(num)
    output = raindropify(factors)

    if output.empty?
      num.to_s
    else
      output.join("")
    end
  end

  def self.raindropify(factors)
    output = []
    output << 'Pling' if factors.include?(3)
    output << 'Plang' if factors.include?(5)
    output << 'Plong' if factors.include?(7)
    output
  end

end

class FactorFactory
  def self.find_each_factor(num)
    (2..num).select { |i| (num % i == 0) }
  end
end
