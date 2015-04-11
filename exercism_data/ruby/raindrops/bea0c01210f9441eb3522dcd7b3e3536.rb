class Raindrops
  def self.convert(number)
    Raindrops.new(number).to_s
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

  def to_s
    @factors.empty? ? @number.to_s : factors_sound
  end

  private
  def number_factors
    FACTORS_TO_CHECK.select { |n| (@number % n).zero? }
  end

  def factors_sound
    @factors.each.map { |n| FACTORS_SOUNDS[n] }.join
  end
end
