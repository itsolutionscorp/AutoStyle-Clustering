class Hamming
  def self.compute(seq_a, seq_b)
    raise ArgumentError unless seq_a.length == seq_b.length

    hamming_distance = 0

    seq_a.chars.zip(seq_b.chars) do |char_pair|
      hamming_distance += 1 unless char_pair.first == char_pair.last
    end

    hamming_distance
  end
end
