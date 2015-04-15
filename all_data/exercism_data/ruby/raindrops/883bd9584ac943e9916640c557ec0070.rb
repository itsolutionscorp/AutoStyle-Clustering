require 'prime'

module Raindrops
  module_function

  def convert(number)
    return number.to_s if prime_factors(number).empty?
    prime_factors(number).map { |factor| word_for(factor) }.join
  end

  def prime_factors(number)
    conditions.keys.select { |x| number % x == 0 }
  end

  def word_for(number)
    conditions.fetch(number)
  end

  def conditions
    {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
  end
end
