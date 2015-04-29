class Hamming
  def self.compute left_strand, right_strand
    new(left_strand, right_strand).hamming_distance
  end

  def initialize left_strand, right_strand
    @left_strand = left_strand.chars
    @right_strand = right_strand.chars
  end

  def hamming_distance
    paired_strands.count do |left_nucleotide, right_nucleotide|
      mutation?(left_nucleotide, right_nucleotide)
    end
  end

  def paired_strands
    truncate(@left_strand).zip truncate(@right_strand)
  end

  def truncate strand
    strand.take shortest_sequence_size
  end

  def shortest_sequence_size
    [@left_strand.size, @right_strand.size].min
  end

  def mutation? left, right
    left != right
  end
end
