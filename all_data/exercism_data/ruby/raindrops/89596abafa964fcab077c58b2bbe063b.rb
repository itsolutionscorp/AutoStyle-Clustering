class Integer
  def divisible_by?(other)
    self % other == 0
  end
end

class Raindrops

  TRANSLATIONS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    if knows_song?
      song
    else
      number.to_s
    end
  end

  private

  attr_reader :number

  def song
    @song ||= TRANSLATIONS.select { |factor, translation|
      number.divisible_by?(factor)
    }.values.join
  end

  def knows_song?
    !song.empty?
  end
end
