require 'mathn'

module Raindrops
  class << self
    def convert(i)
      Raindrop.new(i).sound
    end
  end
end

class Raindrop
  def initialize(i)
    @integer = i
    @prime_factors = Prime.prime_division(i).flatten
    @s ||= String.new
  end

  def sound
    { 'Pling' => 3,
      'Plang' => 5,
      'Plong' => 7 }.each do |k,v|
      convert k, v
    end

    return ( (not @s.empty?) ? @s : @integer.to_s )
  end

  def convert(d, p)
    @s = @s.+ d if @prime_factors.include?(p)
  end

  private :convert
end
