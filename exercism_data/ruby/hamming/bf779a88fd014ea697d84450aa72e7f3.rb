# Pseudocode
# Need to make a program that can calculate the difference between two DNA strand
# Only going to worry about sequences of equal length
# If a user inputs 2 strings of letters - a sequence
# Return an integer that is the amount of differences between the two strings


# New algorithm idea: Compare at each index up to a certain distance.
# Checks up to the index of the shortest strand assuming they aren't equal length.
# For loop or each?

class Hamming
  def self.compute(string1, string2)
    strand_one = string1.split("")
    strand_two = string2.split("")
    @hamming_distance = 0

    if strand_one.length == strand_two.length
      measure_length(strand_one, strand_two)
      iterate_through_strands(strand_one, strand_two)
      @hamming_distance

    elsif strand_two.length > strand_one.length
      measure_length(strand_one, strand_two)
      iterate_through_strands(strand_one, strand_two)
      @hamming_distance

    elsif strand_one.length > strand_two.length
      measure_length(strand_one, strand_two)
      iterate_through_strands(strand_one, strand_two)
      @hamming_distance
    end
  end

  def self.iterate_through_strands(strand_one, strand_two)
    for i in 0..measure_length(strand_one, strand_two)
      if strand_one[i] != strand_two[i]
        @hamming_distance += 1
      end
    end
  end

  def self.measure_length(strand_one, strand_two)
    if strand_one.length > strand_two.length
      strand_two.length - 1
    elsif strand_two.length > strand_one.length
      strand_one.length - 1
    elsif strand_one.length == strand_two.length
      strand_one.length - 1
    end
  end
end
