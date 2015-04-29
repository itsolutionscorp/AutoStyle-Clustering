require 'prime'

##
# Converts numbers to raindrop-speak.

class Raindrops

  ##
  # Convert the given number to raindrop-speak.

  def self.convert(number)

    factors = Prime.prime_division(number).collect { |n| n[0] }

    three = factors.include?(3)
    five = factors.include?(5)
    seven = factors.include?(7)

    output = ''
    output += 'Pling' if three
    output += 'Plang' if five
    output += 'Plong' if seven
    if !three && !five && !seven
      output += number.to_s
    end

    output

  end

end
