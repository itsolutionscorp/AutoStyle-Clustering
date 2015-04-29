# Class to manage transcriptions beetween RNA and DNA strands
class Complement

  # Nucleotide complements for RNA
  RNA_COMPLEMENT = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  # Nucleotide complements for DNA
  DNA_COMPLEMENT = {
    "C" => "G",
    "G" => "C",
    "A" => "T",
    "U" => "A"
  }

  # Get the transcribed RNA strand from a DNA strand
  def self.of_dna(dna_strand)
    rna_strand = ""
    dna_strand.each_char do |dna_nucleotide|
      rna_strand += RNA_COMPLEMENT[dna_nucleotide]
    end
    rna_strand
  end

  # Get the transcribed DNA strand from a RNA strand
  def self.of_rna(rna_strand)
    dna_strand = ""
    rna_strand.each_char do |rna_nucleotide|
      dna_strand += DNA_COMPLEMENT[rna_nucleotide]
    end
    dna_strand
  end
end
