class Complement

  TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  TO_DNA = { 'U' => 'A', 'A' => 'T', 'G' => 'C', 'C' => 'G' }

  def self.of_dna dna_strand
    convert_nucleotides_of dna_strand, TO_RNA
  end

  def self.of_rna rna_strand
    convert_nucleotides_of rna_strand, TO_DNA
  end

  private

  def self.convert_nucleotides_of strand, lexicon
    pattern = Regexp.new lexicon.keys.join("|")
    strand.gsub(pattern) {|match| lexicon[match] }
  end

end
