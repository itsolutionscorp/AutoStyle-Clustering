module Complement
  def self.of_dna(rna)
  	rna.chars.each_with_object([]) { |c, dna| dna << DNA_TO_RNA[c] }.join
  end

  def self.of_rna(dna)
  	dna.chars.each_with_object([]) { |c, rna| rna << RNA_TO_DNA[c] }.join
  end

  private

  DNA_TO_RNA = {
  	'G' => 'C',
  	'C' => 'G',
  	'T' => 'A',
  	'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert
end
