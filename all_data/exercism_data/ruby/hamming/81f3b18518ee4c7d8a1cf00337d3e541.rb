class DNA
  attr_accessor :given_dna_sequence
  attr_accessor :dna_sequence_to_compare

  def initialize dna_sequence
    @given_dna_sequence = DNA_Sequence.new(dna_sequence)
  end

  def hamming_distance dna_sequence
    @dna_sequence_to_compare = DNA_Sequence.new(dna_sequence)

    fix_length
    count_point_mutations
  end

  private

  def fix_length
    if given_dna_sequence.length > dna_sequence_to_compare.length
      given_dna_sequence.limit_length_to dna_sequence_to_compare.length
    elsif dna_sequence_to_compare.length > given_dna_sequence.length
      dna_sequence_to_compare.pad(dna_sequence_to_compare.length - given_dna_sequence.length)
    end
  end

  def count_point_mutations
    count = 0

    given_dna_sequence.strand.chars.each_with_index do |char, index|
      if char != dna_sequence_to_compare.strand[index]
        count += 1
      end
    end
    count
  end
end

class DNA_Sequence
  attr_accessor :strand

  def initialize dna_sequence
    @strand = dna_sequence
  end

  def pad(length)
    (1...length).each { strand << ' ' }
  end

  def limit_length_to(length)
    @strand = strand.slice(0, length)
  end

  def length
    strand.length
  end
end
