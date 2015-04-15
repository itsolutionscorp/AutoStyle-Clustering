class Complement
  DNA_TO_RNA = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
  RNA_TO_DNA = {'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A'}

  def self.of_dna(nucleotide)
    i = 0
    max = nucleotide.length
    output = ""
    while i < max do
      output += DNA_TO_RNA[nucleotide[i]]
      i += 1
    end
    return output
  end

  def self.of_rna(nucleotide)
    i = 0
    max = nucleotide.length
    output = ""
    while i < max do
      output += RNA_TO_DNA[nucleotide[i]]
      i += 1
    end
    return output
  end    
end
