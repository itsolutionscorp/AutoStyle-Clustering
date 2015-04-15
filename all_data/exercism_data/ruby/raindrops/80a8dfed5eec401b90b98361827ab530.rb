# Public: A program that converts a number to a string, the contents of which depends on the number's prime factors.

# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
# - If the number does not contain 3, 5, or 7 as a prime factor,
#   just pass the number's digits straight through.
class Raindrops
  require 'prime'

  class << self

    # Public: Converts the supplied number to a string reflecting the 
    #         Raindrops for the values. 
    # 
    # num - Integer to convert
    #
    # Returns a String, either the Raindrop sounds or the num
    def convert(num)
      raindrop = ''
      Prime.prime_division(num).each do | factor, count |
        raindrop << get_drop(factor)
      end
      raindrop.empty? ? num.to_s : raindrop
    end


    private
      # Internal: A method to return a Raindrop sound for a specific number
      #
      # num - Integer value
      #
      # Returns a String for the sound representing the num
      # Returns an empty string` if no sound associted with the num
      #
      def get_drop(num)
        sounds = {
          3 => 'Pling',
          5 => 'Plang',
          7 => 'Plong'
        }
        sounds[num] || ''
      end

  end
end
