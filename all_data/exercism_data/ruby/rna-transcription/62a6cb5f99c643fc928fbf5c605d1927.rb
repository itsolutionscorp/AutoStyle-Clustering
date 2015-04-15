class Complement
  TO_RNA = %w(G C T A).zip(%w(C G A U)).to_h
  TO_DNA = TO_RNA.invert

  def self.of_dna(strand)
    strand.chars.map { |n| TO_RNA.fetch(n) { raise ArgumentError } }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |n| TO_DNA.fetch(n) { raise ArgumentError } }.join
  end
end
