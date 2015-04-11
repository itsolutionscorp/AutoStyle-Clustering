require 'contracts'

module Raindrops
  include Contracts

  Contract Num => String
  def self.convert(number)
    sounds = sounds_for(prime_factors_of(number))

    if sounds.empty?
      number.to_s
    else
      sounds
    end
  end

  private

  # Find the sounds associated with this list of factors
  # Look through the list of sounds and select those that are present in the input
  # Join all these sounds together into a string and return it
  Contract ArrayOf[Num] => String
  def self.sounds_for(factors)
    noisy_factors = factor_sounds.select{|num| factors.include?(num[:factor])}
    song = noisy_factors.map{|num| num[:sound]}.join
    song
  end

  # Find the prime factors of a number recursively
  # Divide the starting number until we find a factor, then continue dividing
  # the result until we can't anymore.
  # Example: 60 => [2, 2, 3, 5]
  Contract Num => ArrayOf[Num]
  def self.prime_factors_of(number)
    if number == 1
      return []
    end
    2.upto(number) do |denom|
      if number % denom == 0
        return [denom] + prime_factors_of(number / denom)
      end
    end
  end

  # Define the sound that a factor makes
  def self.factor_sounds
    [{:factor => 3, :sound => "Pling"},
     {:factor => 5, :sound => "Plang"},
     {:factor => 7, :sound => "Plong"}]
  end
end
