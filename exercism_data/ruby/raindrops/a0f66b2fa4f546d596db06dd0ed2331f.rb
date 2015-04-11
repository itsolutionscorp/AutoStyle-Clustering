# Raindrops
# Converts a number to a string, the contents of which depends on the number's
# prime factors.
# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
# - Return the number as a string otherwise
class Raindrops
  def self.convert(num)
    fail ArgumentError unless num.is_a?(Fixnum) || num.is_a?(Bignum)
    output = ''
    output << 'Pling' if num % 3 == 0
    output << 'Plang' if num % 5 == 0
    output << 'Plong' if num % 7 == 0

    return num.to_s if output.empty?
    return output
  end
end
