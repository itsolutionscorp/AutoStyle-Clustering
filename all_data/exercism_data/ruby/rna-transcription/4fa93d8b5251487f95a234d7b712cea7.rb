class Complement

  def self.of_dna(strand)
    return convert({'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}, strand)
  end

  def self.of_rna(strand)
    return convert({'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A'}, strand)
  end

  def convert(change, strand)
    result = ''
    strand.split("").each do |nucleotide|
      result += change[nucleotide]
    end
    return result
  end

end
