class Complement
  DNA_TO_RNA = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
  RNA_TO_DNA = {'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A'}

  def self.translate(nucleotide, type)
    i = 0
    max = nucleotide.length
    output = ""
    while i < max do
      output += type[nucleotide[i]]
      i += 1
    end
    return output
  end

  def self.of_dna(nucleotide)
    translate(nucleotide, DNA_TO_RNA)
  end

  def self.of_rna(nucleotide)
    translate(nucleotide, RNA_TO_DNA)
  end    
end
