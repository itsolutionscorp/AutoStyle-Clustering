require 'prime'

class Raindrops

  def self.convert(num)
    relevant_factors = (self.factors(num) & [3,5,7])
    output = []
    output << 'Pling' if relevant_factors.include? 3
    output << 'Plang' if relevant_factors.include? 5
    output << 'Plong' if relevant_factors.include? 7
    output << num.to_s if relevant_factors.empty?
    output.join("")
  end

  def self.factors(num)
    pairs = Prime.prime_division(num)
    factors = []
    pairs.map do |pair|
      pair[1].times { factors << pair[0] }
    end
    factors
  end

end
