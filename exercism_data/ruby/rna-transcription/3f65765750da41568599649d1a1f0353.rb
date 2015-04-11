class Complement
  def self.of_dna(dna_strand)
    self.of_strand(dna_strand, DNA_RNA)
  end

  def self.of_rna(rna_strand)
    self.of_strand(rna_strand, RNA_DNA)
  end

  private 
    DNA_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
    RNA_DNA = { "C" => "G", "G" => "C", "A" => "T", "U" => "A" }

    def self.of_strand(strand, mapping)
      strand.chars.map{ |nucleotide| mapping[nucleotide] }.join
    end
end
