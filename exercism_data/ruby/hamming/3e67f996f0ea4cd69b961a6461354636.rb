# Hammming
# This class computes the hamming distance between valid string pairs
# Hamming distance is simply the number of string units that differ between
# either string
class Hamming

  # This error class is used on reciept of invalid string pairs
  # as arguments in +Hamming.compute(string1, string2)+
  class InvalidStringError < StandardError
    def message
      'Base pair string is limited to the alphabet [ACGT]'
    end
  end

  # Returns the hamming distance between two strings +string1+, +string2+
  # Returns +InvalidStringError+ if at least one of the following con
  # ditions are fulfilled:
  # - strings are of unequal length
  # - They use an alphabet other than {A, C, G, T}
  def self.compute(string1, string2)
    distance = 0
    string1, string2 = string1.upcase, string2.upcase
    fail InvalidStringError if bad_sequence?(string1) || bad_sequence?(string2)
    fail InvalidStringError if string1.length != string2.length

    ary1, ary2 = string1.chars, string2.chars
    ary1.zip(ary2) { |byte1, byte2| distance += 1 unless byte1 == byte2 }

    return distance
  end

  private

  # Checks if the base pair string contains any
  #  alphabets other than {A, C, G, T}
  def self.bad_sequence?(str)
    return !!str.match(/[^ACGT]/i)
  end
end
