class Raindrops
  attr_reader :number
  RAINDROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(number)
    Raindrops.new(number).to_s
  end

  def initialize(number)
    @number = number
  end

  def factors
    @factors ||= RAINDROPS.keys.keep_if { |n| factor?(n) }
  end

  def factor?(factor)
    number % factor == 0
  end

  def raindrop_string(factors)
    factors.collect { |f| RAINDROPS[f] }.join
  end

  def to_s
    factors.any? ? raindrop_string(factors) : number.to_s
  end
end
