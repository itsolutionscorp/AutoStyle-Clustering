require 'prime'

class Raindrops

  attr_reader :number, :prime_factors

  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
    @number = number
    @prime_factors = prime_factorization
  end

  def convert
    describe_sound? ? sound_word : number.to_s
  end

  private

  def prime_factorization
    Prime.prime_division(number).flatten
  end

  def describe_sound?
    prime_factors.any?{ |prime_factor| [3, 5, 7].include?(prime_factor) }
  end

  def sound_word
    [pling, plang, plong].compact.join
  end

  def pling
    'Pling' if prime_factors.include?(3)
  end

  def plang
    'Plang' if prime_factors.include?(5)
  end

  def plong
    'Plong' if prime_factors.include?(7)
  end

end
