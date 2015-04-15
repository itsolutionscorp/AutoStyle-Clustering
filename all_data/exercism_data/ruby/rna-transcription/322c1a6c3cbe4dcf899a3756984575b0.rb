class Complement

  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(dna)
    get_complement(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    get_complement(rna, DNA_TO_RNA.invert)
  end

  private

    def self.get_complement(string, hash)
      string.chars.map{|c|hash[c]}.join
    end

end
