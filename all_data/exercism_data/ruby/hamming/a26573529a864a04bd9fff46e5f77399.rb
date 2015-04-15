class Hamming
  def self.compute dna, dna_to_compare
    distance = 0
    (0..dna.length).each do |x|
      distance += 1 if dna[x] != dna_to_compare[x]
    end
    distance
  end
end
