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
    output = ""
    @sounds.each do |num, sound|
      if factors.include?(num)
        output += sound
      end
    end
    if output != ""
      output
    else
      drops.to_s
    end
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
