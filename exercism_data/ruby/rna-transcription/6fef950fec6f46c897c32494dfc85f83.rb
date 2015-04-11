class Complement

  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = DNA_TO_RNA.map{|k,v|[v,k]}.to_h

  def self.of_dna(dna)
    get_complement(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    get_complement(rna, RNA_TO_DNA)
  end

  private

    def self.get_complement(string, hash)
      string.chars.map{|c|hash[c]}.join
    end

end
