class Hamming
  def self.compute(old_dna, new_dna)
    hamming_distance = 0
    old_dna.split(//).each_with_index do | char , index |
      break if new_dna.length == index
      hamming_distance += 1 unless char == new_dna[index]
    end
    hamming_distance
  end
end
