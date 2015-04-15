class Complement
  DNA_TO_RNA = { 'A' => 'U', 'T' => 'A', 'C' => 'G', 'G' => 'C'}
  RNA_TO_DNA = { 'A' => 'T', 'U' => 'A', 'C' => 'G', 'G' => 'C'}

  class << self
    def of_dna(dna_string)
      dna_string.chars.map{|nucleotide| DNA_TO_RNA[nucleotide]}.join
    end

    def of_rna(rna_string)
      rna_string.chars.map{|nucleotide| RNA_TO_DNA[nucleotide]}.join
    end
  end
end
