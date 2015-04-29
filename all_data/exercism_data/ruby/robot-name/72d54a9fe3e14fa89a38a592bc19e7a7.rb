require 'singleton'

# This class should (emphasis on "should") generate
# 2,704,000 unique robot names in a non-repeating,
# pseudo-random fashion.
#
# Adapted from: http://preshing.com/20121224/how-to-generate-a-sequence-of-unique-random-integers/
class RobotNameGenerator
  include Singleton

  # 2x fifty-two letters, 3 digits = 2,704,000 combinations
  BOUNDARY = 52 * 52 * 1000

  # Largest prime below BOUNDARY that also satifies p % 4 == 3
  PRIME = 2703983

  # Another prime used as step
  STEP = 23

  # Initializes the instance, and sets a random offset (seed value)
  def initialize
    @offset = rand(0...BOUNDARY)
  end

  # Get a new pseudo-random name
  def next
    serial = next_serial_number
    serial, digits = serial.divmod(1000)
    serial, b = serial.divmod(52)
    serial, a = serial.divmod(52)
    "%s%s%03i" % [letter_for(a), letter_for(b), digits]
  end

  private

  # Get the next serial number
  def next_serial_number
    serial = permute(permute(@offset % BOUNDARY))
    @offset += STEP
    serial
  end

  # Quadratic Prime Residue!
  def permute(n)
    return n if n > PRIME
    residue = n**2 % PRIME
    n <= PRIME / 2 ? residue : PRIME - residue
  end

  # Encode a 0-51 number to A-Z/a-z
  def letter_for(n)
    offset = n < 26 ? 'A'.ord : 'a'.ord
    ascii = offset + (n % 26)
    ascii.chr
  end
end

# The robot class - not much to see here
class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = RobotNameGenerator.instance.next
  end
end
