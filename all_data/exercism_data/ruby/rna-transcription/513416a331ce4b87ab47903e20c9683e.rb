class Complement
  DNA_TO_RNA = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U' }.freeze
  RNA_TO_DNA = DNA_TO_RNA.invert.freeze

  def self.of_dna(dna)
    replace(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    replace(rna, RNA_TO_DNA)
  end

  private

    def self.replace(strand, converter)
      strand.split("").map do |nucleotide|
        converter[nucleotide]
      end.join
    end
end
