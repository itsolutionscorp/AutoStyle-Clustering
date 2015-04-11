class Complement

  def self.of_dna(strand)
    change = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    return convert(change, strand)
  end

  def self.of_rna(strand)
    change = {'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A', }
    return convert(change, strand)
  end

  def convert(change, strand)
     result = ''
     strand.split("").each do |nucleotide|
      result += change[nucleotide]
    end
    return result
  end

end
