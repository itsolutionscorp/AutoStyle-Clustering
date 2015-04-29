DNA_TO_RNA_MAPPING = {"A" => "U" , "C" => "G", "G" => "C", "T" => "A" }

class Complement

  def self.of_rna(rna_strand)
    rna_strand.chars.map{ |rna_nucleotide| DNA_TO_RNA_MAPPING.key(rna_nucleotide) }.join
  end


  def self.of_dna(dna_strand)
    dna_strand.chars.map{ |dna_nucleotide| DNA_TO_RNA_MAPPING[dna_nucleotide] }.join
  end

end
