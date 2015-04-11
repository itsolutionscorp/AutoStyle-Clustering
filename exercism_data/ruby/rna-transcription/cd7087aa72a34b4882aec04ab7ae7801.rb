class Complement

  DNA_TO_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(nucleotide)
    final = ""
    nucleotide.each_char do |n|
      raise ArgumentError if n == 'U'
      final << DNA_TO_RNA[n]
    end
    final
  end

  def self.of_rna(nucleotide)
    final = ""
    nucleotide.each_char do |n|
      raise ArgumentError if n == 'T'
      final << RNA_TO_DNA[n]
    end
    final
  end

end
