require 'prime'

class Raindrops
  def self.convert(number)
    output = ""
    raise ArgumentError,
      'Argument is not numeric' unless number.is_a? Numeric
    if number % 1 == 0 && number != 0
      number = number.to_i
      dictionary = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
      factors = number.prime_division.flatten
      factors.delete(1)
      dictionary.each { |k, v| output <<  v if factors.include?(k) }
      factors.empty? || output.empty? ? number.to_s : output
    else
      number.to_s
    end
  end
end
