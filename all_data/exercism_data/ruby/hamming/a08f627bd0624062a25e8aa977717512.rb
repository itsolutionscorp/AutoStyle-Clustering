module Hamming
  # Compute the Hamming distance between two DNA sequences.
  def self.compute(string1, string2)
    sequence1 = DnaSequence.new string1
    sequence2 = DnaSequence.new string2

    sequence1.hamming_distance sequence2
  end

  class DnaSequence
    attr_accessor :sequence_string

    VALID_CHARS = ["A","C","G","T"]

    # Determine if the given string is a valid DNA sequence
    # by ensuring that it only contains a subset of the VALID_CHARS
    # list.
    def self.valid_sequence?(sequence_string)
      chars = sequence_string.chars.uniq
      (chars & VALID_CHARS) == chars
    end

    # Create a new DnaSequence object.
    # Raises a RuntimeError if the sequence string provided is
    # not a valid DNA sequence.
    def initialize(sequence_string)
      self.sequence_string = sequence_string.upcase

      unless DnaSequence.valid_sequence?(self.sequence_string)
        raise "Invalid DNA sequence string."
      end
    end

    # Compute the Hamming distance between this DnaSequence and a
    # second DnaSequence.
    def hamming_distance(dna_sequence)
      other_sequence = dna_sequence.sequence_string
      distance = 0

      sequence_string.chars.each_with_index do |char, index|
        break if other_sequence[index] == nil
        distance += 1 unless other_sequence[index] == char
      end

      distance
    end
  end
end
