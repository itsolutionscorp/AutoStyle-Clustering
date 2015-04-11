require 'pry'

DNA_TO_RNA_MAP = {
  'G' => 'C',
  'C' => 'G',
  'T' => 'A',
  'A' => 'U'
}

class Complement
  def self.of_dna(dna_strand)
    dna_strand_nucleotides = dna_strand.chars
    dna_strand_nucleotides.map {|dna_nucleotide| DNA_TO_RNA_MAP[dna_nucleotide]}.join
  end

  def self.of_rna(rna_strand)
    rna_strand_nucleotides = rna_strand.chars
    rna_strand_nucleotides.map do |rna_nucleotide|
      DNA_TO_RNA_MAP.find{|k,v| v == rna_nucleotide }.first
    end.join
  end
end
