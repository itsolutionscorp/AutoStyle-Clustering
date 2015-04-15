module Hamming
  class UnequalSequenceLengthError < StandardError
  end

  def Hamming.compute(sequence_a, sequence_b)
    self.ensure_equal_length(sequence_a, sequence_b)

    # can change .split('') to .chars when I've upgraded to ruby 2.0
    zipped_sequence = ZippedSequence.new(sequence_a.split(''), sequence_b.split(''))
    zipped_sequence.no_of_differences
  end

  def Hamming.ensure_equal_length(sequence_a, sequence_b)
    if sequence_a.length != sequence_b.length 
      raise UnequalSequenceLengthError, "Sequences that you are comparing must be of equal lengths"
    end
  end
end

class Pair
  def initialize(item_a, item_b)
    @pair = [item_a, item_b]
  end

  def identical_members?
    @pair[0] == @pair[1]
  end
end

class ZippedSequence
  def initialize(sequence_a, sequence_b)
    @sequence = []
    sequence_a.each.with_index do |item, index|
      @sequence << Pair.new(item, sequence_b[index])
    end
  end

  def no_of_differences
    @sequence.count do |pair|
      not pair.identical_members?
    end
  end
end
