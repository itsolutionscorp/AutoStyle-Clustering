class DNA
  BASES = %w{A T C G}
  BASES_PLUS_RNA = BASES + ['U']

  def initialize(base_chain)
    @chain = base_chain
  end

  def count(base)
    raise ArgumentError, "invalid base #{base}" unless BASES_PLUS_RNA.include?(base)
    @chain.scan(base).count
  end

  def nucleotide_counts
    BASES.each_with_object({}) do |base, h|
      h[base] = count(base)
    end
  end
end
