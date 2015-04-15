class Raindrops
  
  def self.convert(drop)
    Raindrop.new(drop).to_s
  end

end

class Raindrop
  
  FACTOR_STRING_LUT = { "3" => "Pling", "5" => "Plang", "7" => "Plong" }.freeze
  FACTORS = FACTOR_STRING_LUT.keys.map(&:to_i).freeze

  attr_reader :drop

  def initialize(drop)
    @drop = drop
  end
  
  def divisible_by?(divisor)
    (drop % divisor) == 0
  end
  
  def factors
    @factors ||= FACTORS.select{|divisor| divisible_by?(divisor)}
  end

  def factor_to_string(f)
    FACTOR_STRING_LUT[f.to_s]
  end
    
  def to_s
    if factors
      factors.map{|f| factor_to_string(f)}.join
    else
      drop.to_s
    end
  end

end
