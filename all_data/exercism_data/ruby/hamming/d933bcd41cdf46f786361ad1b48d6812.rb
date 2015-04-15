class Hamming
  def self.compute dna, dna_to_compare
    distance = 0
    (0..dna.length).each { |x| distance += 1 unless dna[x].eql?(dna_to_compare[x]) }
    distance
  end
end
