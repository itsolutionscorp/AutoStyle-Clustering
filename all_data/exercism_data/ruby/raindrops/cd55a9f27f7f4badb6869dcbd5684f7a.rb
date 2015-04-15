require 'prime'

class Raindrops

  attr_reader :drops, :sounds, :factors

  def self.convert drops
    converter = new(drops).conversion
  end

  def initialize drops
    @drops = drops
    @sounds = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    @factors = factor_array_from(drops)
  end

  def conversion 
    @sounds.select { |num, sound| factors.include?(num) }.values.join[/P.*/] || drops.to_s
  end

  def factor_array_from num
    Prime.each do |prime|
      if prime > num
        return []
      elsif (num % prime) == 0
        return [prime].concat(factor_array_from(num / prime))
      end
    end
  end

end
