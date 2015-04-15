require "prime"
class Raindrops
  def self.convert(n)
    factors = Prime.prime_division(n)
    output = '' 

    unless factors.empty?
      output << 'Pling' if factors.map { |n| n.include?(3)}.include?(true)
      output << 'Plang' if factors.map { |n| n.include?(5)}.include?(true)
      output << 'Plong' if factors.map { |n| n.include?(7)}.include?(true)
    end
    
    output.empty? ? n.to_s : output
  end
end
