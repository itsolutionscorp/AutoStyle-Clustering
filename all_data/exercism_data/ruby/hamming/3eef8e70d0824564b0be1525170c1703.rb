class Hamming
  def self.compute(top_dna, bottom_dna)
    distance_count = 0

    [top_dna.length, bottom_dna.length].min.times do |dna|
      unless top_dna[dna] == bottom_dna[dna]
        distance_count += 1
      end
    end
   distance_count
  end
end
