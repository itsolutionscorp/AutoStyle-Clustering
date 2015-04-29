class Hamming
  def self.compute(left = "", right = "")
    sequence_a, sequence_b, distance = left.chars, right.chars, 0

    sequence_a.zip(sequence_b).each do |a_nucleotide, b_nucleotide|
      unless a_nucleotide.nil? || b_nucleotide.nil?
        (distance += 1) unless a_nucleotide == b_nucleotide
      end
    end

    distance
  end
end
