class Complement
  DNA_TO_RNA = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA = { 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T' }

  def self.of_dna(nucleotides)
    translate_strand(nucleotides.chars, DNA_TO_RNA).join('')
  end

  def self.of_rna(nucleotides)
    translate_strand(nucleotides.chars, RNA_TO_DNA).join('')
  end

  class << self
    private

    def translate_strand(nucleotides, translation)
      nucleotides.map { |n| translation.fetch(n) { raise ArgumentError } }
    end
  end
end
