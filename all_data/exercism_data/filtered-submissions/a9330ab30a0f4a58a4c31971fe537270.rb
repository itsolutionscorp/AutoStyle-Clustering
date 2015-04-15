module Hamming
  def self.distance *sequences
    (first, second) = sequences.map &:chars
    shortest_length = sequences.map(&:size).min
    first.zip(second).take(shortest_length).count do |first_char, second_char|
      first_char != second_char
    end
  end
end
