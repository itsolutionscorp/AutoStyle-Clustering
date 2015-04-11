class HashUtility

  def self.from_to(from, to)
    Hash[from.map.with_index {|element, index| [element, to[index]]}]
  end

end

class Complement
  
  DNA_NUCLEOTIDES = ['G', 'C', 'T', 'A']
  RNA_NUCLEOTIDES = ['C', 'G', 'A', 'U']
  
  DNA_TO_RNA = HashUtility.from_to(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  RNA_TO_DNA = HashUtility.from_to(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)

  def self.of_dna(dna_strand)
    rna_strand = ""
    dna_strand.each_char do |char|
      nucleotide = DNA_TO_RNA[char]
      if nucleotide.nil?
        raise(ArgumentError, "#{char} is not a DNA-Nucleotide.")
      end
      rna_strand += nucleotide
    end
    rna_strand
  end

  def self.of_rna(rna_strand)
    dna_strand = ""
    rna_strand.each_char do |char|
      nucleotide = RNA_TO_DNA[char]
      if nucleotide.nil?
        raise(ArgumentError, "#{char} is not a RNA-Nucleotide.")
      end
      dna_strand += nucleotide
    end
    dna_strand
  end
end
