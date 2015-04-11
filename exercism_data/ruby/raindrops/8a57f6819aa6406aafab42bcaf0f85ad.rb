require 'prime'

class Raindrops
  def self.convert(number)
    output = ""
    raise ArgumentError, 'Number is not integer' unless number.is_a? Integer
    dictionary = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    factors = number.prime_division.flatten
    factors.delete(1)
    dictionary.each { |k, v| output <<  v if factors.include?(k) }
    factors.empty? || output.empty? ? number.to_s : output
  end
end
