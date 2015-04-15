class Complement

  def self.of_dna(dna_strand)
    dna = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U',}

    dna_strand.chars.map do |nucleotide|
      if dna[nucleotide].nil?
        raise(ArgumentError)
      else
        dna[nucleotide]
      end
    end.join

  end

  def self.of_rna(rna_strand)
    rna = {'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T'}

    rna_strand.chars.map do |nucleotide|
      if rna[nucleotide].nil?
        raise(ArgumentError)
      else
        rna[nucleotide]
      end
    end.join
  end

end
