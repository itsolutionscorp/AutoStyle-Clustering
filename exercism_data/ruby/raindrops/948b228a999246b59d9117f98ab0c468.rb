require 'forwardable'

module Raindrops

  def self.convert(num)
    Drop.new(num).to_s
  end

end

class Drop

  RAIN_SOUNDS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }
  extend Forwardable
  delegate factors: :factorizer

  def initialize(integer, factorizer_class = Factorizer)
    @integer          = integer
    @factorizer_class = factorizer_class
  end

  def to_i
    @integer
  end

  def to_s
    sound? ? to_a.join : to_i.to_s
  end

  def to_a
    factors.map { |factor| RAIN_SOUNDS[factor] }
  end

  def factorizer
    @factorizer_class.new to_i
  end

  def sound?
    !(factors & RAIN_SOUNDS.keys).empty?
  end

end

class Factorizer
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def factors
    (1..subject).select { |elm| subject % elm == 0 }
  end

end
