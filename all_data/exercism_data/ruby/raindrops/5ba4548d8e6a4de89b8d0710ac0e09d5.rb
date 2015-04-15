class Raindrops
  def self.convert(number)
    Raindrops.new(number).speak
  end

  FACTORS_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  FACTORS_TO_CHECK = FACTORS_SOUNDS.keys.sort

  def initialize(number)
    @number = number
    @factors = number_factors
  end

  def speak
    @factors.empty? ? @number.to_s : sound
  end

  private
  def number_factors
    FACTORS_TO_CHECK.select { |n| (@number % n).zero? }
  end

  def sound
    @factors.each.map { |r| FACTORS_SOUNDS[r] }.join
  end
end
