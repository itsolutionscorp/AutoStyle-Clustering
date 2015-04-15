class Complement

  DNA_RNA_CONVERTER = { 'G'=>'C','C'=>'G','T'=>'A','A'=>'U' }

  def self.of_dna(rna_strand)
    converted_strand = ""
    rna_strand.each_char { |nucleotide| converted_strand << DNA_RNA_CONVERTER[nucleotide] }
    converted_strand
  end

  def self.of_rna(dna_strand)
    converted_strand = ""
    dna_strand.each_char { |nucleotide| converted_strand << DNA_RNA_CONVERTER.key(nucleotide) }
    converted_strand
  end

end
