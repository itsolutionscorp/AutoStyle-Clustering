class Complement

  def self.of_dna(dna)
    replace(dna, dna_to_rna)
  end

  def self.of_rna(rna)
    replace(rna, rna_to_dna)
  end

  private

    def self.replace(strand, converter)
      nucleotides = strand.split("")
      
      converted_strand = nucleotides.map do |nucleotide|
        converter[nucleotide]
      end

      converted_strand.join("")
    end

    def self.dna_to_rna
      {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
    end

    def self.rna_to_dna
      {'G'=>'C', 'C'=>'G', 'U'=>'A', 'A'=>'T'}
    end
end
