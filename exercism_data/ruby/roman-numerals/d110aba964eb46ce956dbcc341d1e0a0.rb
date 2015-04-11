module Roman
  class Numeral
    attr_reader :value
    def initialize(value)
      @value = value
    end

    # Because calculating numerals might be an expensive
    # operation and because there's no facility to change
    # the number once it's assigned in this class (it's
    # static) the calculated value can be memoized
    def to_s
      @string_value ||= calculate_roman_numeral
    end

    private

    # This calculates the roman numeral by greedily finding
    # the biggest number that will fit into the value then
    # pushing that numeral into a string and subracting the
    # value (and then repeating).
    def calculate_roman_numeral
      remaining_value = value
      roman_string = ''
      while (remaining_value > 0)
        value = biggest_number_that_fits_into(remaining_value)
        roman_string += numbers_to_numerals[value]
        remaining_value -= value
      end
      roman_string
    end

    def biggest_number_that_fits_into(value)
      numbers_ordered_descending.find do |number|
        value - number >= 0
      end
    end

    # This dictionary makes teh decision to remove special
    # cases like 4 by (IV) by simply including them in the
    # dictionary
    def numbers_to_numerals
      {
        1 => "I",
        4 => "IV",
        5 => "V",
        9 => "IX",
        10 => "X",
        40 => "XL",
        50 => "L",
        90 => "XC",
        100 => "C",
        400 => "CD",
        500 => "D",
        900 => "CM",
        1000 => "M"
      }
    end

    def numbers_ordered_descending
      numbers_to_numerals.keys.sort { |a,b| b-a }
    end
  end

  module NumericExtensions
    # Neat trick gleaned from Avdi Grimm's confident ruby
    # using the `to_i` method here means that this can be 
    # mixed into any class that can cast their value to an
    # integer.  This could be mixed into String, for example.
    def to_roman
      Numeral.new(to_i).to_s
    end
  end
end

# In a more realistic implementation of this program this
# would get mixed in when it was required and not automatically
# when the code is loaded.  To fit the needs of the tests,
# though this is automatically loaded
class Numeric
  include Roman::NumericExtensions
end
