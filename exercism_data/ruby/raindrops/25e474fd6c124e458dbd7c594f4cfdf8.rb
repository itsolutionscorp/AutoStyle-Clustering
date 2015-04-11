class Raindrops
  def self.convert(raindrops)
    new(raindrops).convert
  end

  def initialize(raindrops)
    @raindrops = raindrops
  end

  def convert
    factor.any? ? factor.compact.join : raindrops.to_s
  end

  private

  attr_reader :raindrops

  def factor
    song.map { |factor, lyric| raindrops % factor == 0 ? lyric : nil }
  end

  def song
    { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end
end
