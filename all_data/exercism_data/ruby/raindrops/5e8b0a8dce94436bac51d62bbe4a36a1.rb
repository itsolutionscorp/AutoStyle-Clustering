class Raindrops

  SPEAK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    sing == "" ? number.to_s : sing
  end

  private

  attr_reader :number

  def known_drops
    SPEAK.keys.select do |n|
      number % n == 0
    end
  end

  def sing
    known_drops.map { |drop| SPEAK[drop] }.join
  end

end
