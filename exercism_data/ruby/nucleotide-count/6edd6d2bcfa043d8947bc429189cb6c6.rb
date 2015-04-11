class DNA
  def initialize(dna_string)
    raise ArgumentError.new unless dna_string =~ /\A[ATCG]*\Z/
    @dna_string = dna_string
  end

  def nucleotide_counts
    @nucleotide_count ||= {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}.tap do |result_hash|
      @dna_string.chars.each do |nucleotide|
        result_hash[nucleotide] += 1
      end
    end
  end

  def count(nucleotide)
    return 0 if nucleotide == ?U
    nucleotide_counts[nucleotide] or raise ArgumentError.new
  end
end
