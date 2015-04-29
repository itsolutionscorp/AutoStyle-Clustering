class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(alt_sequence)
    count_different_nucleotide_placements(alt_sequence)
  end

  def count_different_nucleotide_placements(alt_sequence)
    nucleotide_pairs(alt_sequence).count do |pair|
      not_equal(pair[0],pair[1]) && !has_nil(pair[0], pair[1])
    end
  end

  def nucleotide_pairs(alt_sequence)
    sequence.chars.zip alt_sequence.chars
  end

  def has_nil(*args)
    args.any? {|arg| arg.nil?}
  end

  def not_equal(arg1, arg2)
    arg1 != arg2
  end
end
