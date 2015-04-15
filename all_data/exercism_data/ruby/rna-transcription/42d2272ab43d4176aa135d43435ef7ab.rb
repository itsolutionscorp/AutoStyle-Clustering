class Complement
  DNA_TO_RNA_COMPLIMENTS = {
  'G' => 'C',
  'C' => 'G',
  'T' => 'A',
  'A' => 'U'
  }

  RNA_TO_DNA_COMPLIMENTS = DNA_TO_RNA_COMPLIMENTS.invert

  def self.of_dna(input_nucleotides)
    nucleotide_map_to_use = DNA_TO_RNA_COMPLIMENTS
    find_compliments(input_nucleotides, nucleotide_map_to_use)
  end

  def self.of_rna(input_nucleotides)
    nucleotide_map_to_use = RNA_TO_DNA_COMPLIMENTS
    find_compliments(input_nucleotides, nucleotide_map_to_use)
  end

  def self.find_compliments(input_nucleotides, nucleotide_map_to_use)
    input_nucleotides.chars.collect!{|nucleotide| nucleotide_map_to_use[nucleotide]}.join
  end

end
