class NucleicAcid
  ALL_BASES = %W(A C G T U)

  def initialize(base_sequence)
    @base_sequence = base_sequence
  end
end

class RNA < NucleicAcid
  def bases
    %W(A C G U)
  end
end

class DNA < NucleicAcid
  def bases
    %W(A C G T)
  end

  def count(base)
    raise ArgumentError, "Unknown base: #{base}" if !ALL_BASES.include? base
    @base_sequence.count base
  end

  def nucleotide_counts
    bases.each_with_object({}) { |base, counts| counts[base] = count base }
  end
end
