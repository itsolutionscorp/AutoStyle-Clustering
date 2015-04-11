class Complement

  def self.of_dna(strand)
    change = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    result = ''
     strand.split("").each do |nucleotide|
      result += change[nucleotide]
    end
    return result
  end

  def self.of_rna(strand)
    change = {'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A', }
    result = ''
    strand.split("").each do |nuc|
      result += change[nuc]
    end
    return result
  end

end
