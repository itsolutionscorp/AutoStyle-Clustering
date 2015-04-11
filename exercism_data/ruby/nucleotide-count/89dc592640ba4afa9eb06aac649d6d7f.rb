class DNA

  @@BASES = "ACGT"
  @@ALL_BASES = "ACGTU"

  def initialize(s)
    if (s.count "^#{@@BASES}") != 0  then
      raise ArgumentError
    end
    @ns = s
  end

  def nucleotide_counts
    @@BASES.chars.each_with_object({}) { |b, h| h[b] = count(b) }
  end

  def count(nuc)
    if ! @@ALL_BASES.include?(nuc) then
      raise ArgumentError
    end
    return @ns.count(nuc)
  end

end
