class DNA < String
  ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL = 'A', 'C', 'G', 'T', 'U'

  def count(base)
    raise ArgumentError, 'That is not a valid nucleobase.' unless is_a_valid_nucleobase(base)
    chars.count { |c| c == base }
  end

  def nucleotide_counts
    initial_counts = { ADENINE => 0, CYTOSINE => 0, GUANINE => 0, THYMINE => 0 }
    chars.each_with_object(initial_counts) { |base, counts| counts[base] += 1 }
  end

  private
  def is_a_valid_nucleobase(base)
    [ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL].include? base
  end
end
