class DNA

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide_letter)
    if nucleotide_letter == "X"
        raise ArgumentError
    end
    @strand.count(nucleotide_letter)
  end 

  def nucleotide_counts
    hashes = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    hashes['A'] = @strand.count("A")
    hashes['T'] = @strand.count("T")
    hashes['C'] = @strand.count("C")
    hashes['G'] = @strand.count("G")
    return hashes
  end
end
