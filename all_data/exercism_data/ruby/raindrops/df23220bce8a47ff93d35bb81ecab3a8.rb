require 'prime'

class Raindrops

  OUTPUT_RELATION = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert number
    new( number ).to_raindrop_speak
  end

  attr_accessor :number

  def initialize number
    @number = number
  end

  def to_raindrop_speak
    raindrop_speak_for number.prime_division
  end

private

  def raindrop_speak_for factorization
    raindrop_sentence = factorization.map{ |prime| output_for prime }.compact 
    
    raindrop_sentence.empty? ? number.to_s : raindrop_sentence.join
  end

  def output_for prime
    OUTPUT_RELATION[ prime.first ]
  end

end
