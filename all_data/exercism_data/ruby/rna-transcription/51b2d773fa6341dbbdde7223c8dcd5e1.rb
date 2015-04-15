# Class to manage transcriptions beetween RNA and DNA strands
class Complement

  # Nucleotide complements for DNA
  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  # Nucleotide complements for RNA
  RNA_TO_DNA = DNA_TO_RNA.invert

  # Get the transcribed RNA strand from a DNA strand
  def self.of_dna(dna_strand)
    dna_strand.chars.map{|dna_nucleotide| DNA_TO_RNA[dna_nucleotide]}.join
  end

  # Get the transcribed DNA strand from a RNA strand
  def self.of_rna(rna_strand)
    rna_strand.chars.map{|rna_nucleotide| RNA_TO_DNA[rna_nucleotide]}.join
  end
end
