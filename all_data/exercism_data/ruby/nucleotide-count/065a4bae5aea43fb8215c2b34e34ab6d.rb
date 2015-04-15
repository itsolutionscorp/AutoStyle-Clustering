class DNA

  def initialize(strand)
    @strand = strand
  end

  def nucleotide_counts
    counts = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }

    @strand.scan(/[GTAC]/).each_with_object(counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  def count(nucleotide)
    raise ArgumentError, "Invalid nucleotide #{nucleotide}" unless /[GATCU]/ =~ nucleotide
    nucleotide_counts[nucleotide] || 0
  end
end
