class Complement
  @@h = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  def self.of_dna(strand)
    strand.split(//).map{ |x| @@h.fetch(x) {raise ArgumentError} }.join
  end
  def self.of_rna(strand)
    strand.split(//).map{ |x| @@h.invert.fetch(x) {raise ArgumentError} }.join
  end
end
