require 'prime'

module Raindrops
  extend self

  def convert(number)
    translation = translate factors_of(number)
    translation.empty? ? number.to_s : translation
  end

  protected

  def translate(factors)
    ''.tap do |t|
      t << 'Pling' if factors.include? 3
      t << 'Plang' if factors.include? 5
      t << 'Plong' if factors.include? 7
    end
  end

  def factors_of(number)
    number.prime_division.flatten.uniq
  end
end
