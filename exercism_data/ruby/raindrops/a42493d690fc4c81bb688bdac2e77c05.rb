class Raindrops
  FACTOR_SOUNDS = {"3" => "Pling",
                   "5" => "Plang",
                   "7" => "Plong"}

  def self.convert(input)
    factors = FACTOR_SOUNDS.select do |divisor, sound|
      divisible_by?(input,divisor.to_i)
    end 
    
    sounds = factors.map { |divisor, sound| sound }

    if sounds.any?
      sounds.join
    else
      input.to_s
    end
  end

  private

  def self.divisible_by?(number, factor)
    (number % factor) == 0
  end
end
