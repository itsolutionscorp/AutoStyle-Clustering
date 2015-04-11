class Complement
  DNA_TO_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(nucleotide)
    set_complement DNA_TO_RNA, nucleotide
  end

  def self.of_rna(nucleotide)
    set_complement RNA_TO_DNA, nucleotide
  end

private
  def self.set_complement(conversion_type, nucleotide)
    final = ""
    nucleotide.each_char do |n|
      raise ArgumentError unless conversion_type.keys.include? n
      final << conversion_type[n]
    end
    final
  end
end
