module Hamming
  def self.distance first, second
    (first, second) = SequenceUtils
                      .cut_to_shortest(first, second)
                      .map(&:chars)

    first.zip(second).count do |first_char, second_char|
      first_char != second_char
    end
  end
end

module SequenceUtils
  def self.cut_to_shortest *sequences
    min_size = sequences.map(&:size).min
    sequences.map do |sequence|
      sequence.slice(0,min_size)
    end
  end
end
