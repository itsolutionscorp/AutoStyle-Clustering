require 'prime'
class Raindrops

  PLINGS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  attr_reader :number, :raindrop

  def self.convert number
    number_to_pling = new number
    number_to_pling.translate
  end

  def initialize number
    @number = number
    @raindrop = ''
  end

  def compute_primes
    number.prime_division
  end

  def plingplangplong
    compute_primes.each do |pair|
     raindrop << PLINGS[pair.first] if PLINGS.member? pair.first
    end
    raindrop.empty? ? number.to_s : raindrop
  end

  def translate
    compute_primes
    plingplangplong
  end
end
