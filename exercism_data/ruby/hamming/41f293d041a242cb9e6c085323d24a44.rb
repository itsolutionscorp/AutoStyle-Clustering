# Compute the Hamming distance between two strands of DNA.
#
# first_string  - the first strand of DNA.
# second_string - the second strand of DNA.
#
# Examples
#
#   Hamming.compute('A', 'A')
#   # => 0
#
#   Hamming.compute('A', 'G')
#   # => 1
#
#   Hamming.compute('AA', 'GC')
#   # => 2
# Returns the Hamming distance between the two given strings.
module Hamming
  def self.compute(first_string, second_string)
    map_differences(first_string, second_string).inject(:+)
  end

  private

  def self.map_differences(first_string, second_string)
    first_string.chars.each_with_index.map do |letter, index|
      single_letter_distance(letter, second_string[index])
    end
  end

  def self.single_letter_distance(first_character, second_character)
    if first_character == second_character
      0
    else
      1
    end
  end
end
