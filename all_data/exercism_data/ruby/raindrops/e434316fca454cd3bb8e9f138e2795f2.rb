require 'prime'

class Raindrops
  def self.convert(number)
    prime_numbers = number.prime_division.to_h.keys
    output = ''

    output << 'Pling' if prime_numbers.include?(3)
    output << 'Plang' if prime_numbers.include?(5)
    output << 'Plong' if prime_numbers.include?(7)

    output.empty? ? number.to_s : output
  end
end
