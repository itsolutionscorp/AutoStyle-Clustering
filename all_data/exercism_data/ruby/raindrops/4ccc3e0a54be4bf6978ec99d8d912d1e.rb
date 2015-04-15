require 'prime'

class Raindrops
  TRANSLATION = {'3' => 'Pling', '5' => 'Plang', '7' => 'Plong'}

  def self.convert(number)
    primes = number.prime_division
                   .map(&:first)
                   .select { |num| TRANSLATION.keys.include?(num.to_s) }

    return number.to_s if primes.empty?

    primes.join.gsub(/[#{TRANSLATION.keys.join}]/, TRANSLATION)
  end
end
