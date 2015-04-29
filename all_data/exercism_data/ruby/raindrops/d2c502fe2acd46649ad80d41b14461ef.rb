require 'prime'

class Raindrops
  SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(number)
    new(number).convert
  end

  attr_reader :n

  def initialize(number)
    @n = number
  end

  def prime_factors
    Prime.prime_division(n).map(&:first)
  end

  def convert
    sounds = prime_factors.map { |x| SOUNDS[x] }.compact

    "#{sounds.join}#{n if sounds.empty?}"
  end
end
