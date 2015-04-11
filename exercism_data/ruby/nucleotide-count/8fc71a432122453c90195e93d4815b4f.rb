class DNA
  def initialize(nucs)
    if !nucs.scan(/[^ATCG]/).empty?
      raise ArgumentError
    else
      @nucs = nucs
      @hash = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    end
  end

  def count(nuc)
    if !["A", "T", "C", "G", "U"].include?(nuc)
      raise ArgumentError
    else
      @nucs.scan(/#{nuc}/).count
    end
  end

  def nucleotide_counts
    @hash.each { |key, value| @hash[key] = count(key) }
    @hash
  end
end
