class Hamming
  def self.compute left_strand, right_strand
    new(left_strand, right_strand).compare_sequences
  end

  def initialize left_strand, right_strand
    @hamming = 0
    @left_strand = left_strand.split ''
    @right_strand = right_strand.split ''
  end

  def compare_sequences
    hamming_distance = 0
    traverse_paired_strands.each do |left_nucleotide, right_nucleotide|
      hamming_distance += 1 if mutation left_nucleotide, right_nucleotide
    end
    hamming_distance
  end

  def traverse_paired_strands
    truncate(@left_strand).zip truncate(@right_strand)
  end

  def truncate strand
    strand.take shortest_sequence_size
  end

  def mutation left, right
    left != right
  end

  def shortest_sequence_size
    [@left_strand.size, @right_strand.size].min
  end
end
