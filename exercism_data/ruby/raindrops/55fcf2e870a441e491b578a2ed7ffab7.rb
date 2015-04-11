require 'prime'
class Raindrops

  def self.convert(number)
    factors = find_factors(number).flatten.uniq.map { |f| raindrop(f) }.join
    factors = number.to_s if factors.empty?
  end

  private
  def self.find_factors(number)
    factors = []
    Prime.each(number) do |prime|
      if(number%prime == 0)
        factors << prime
        factors << find_factors(number/prime)
      end
    end
    factors
  end

  def self.raindrop(factor)
    case factor
      when 3 then
        "Pling"
      when 5 then
        "Plang"
      when 7 then
        "Plong"
    end
  end
end
