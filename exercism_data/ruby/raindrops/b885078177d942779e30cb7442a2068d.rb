class Raindrops
  FACTORS_TO_SOUNDS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong",
  }.freeze

  def self.convert(count)
    new(count).to_s
  end

  attr_reader :count

  def initialize(count)
    @count = count
  end

  def to_s
    sounds.any? ? sounds.join : count.to_s
  end

  def sounds
    @sounds ||=
      FACTORS_TO_SOUNDS.map { |factor, sound|
        sound if count % factor == 0
      }.compact
  end

end
