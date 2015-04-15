class Hamming
  def self.compute(firstDNA, secondDNA)
    hamming_distance = 0

    0.upto([firstDNA.length, secondDNA.length].min - 1) do |i|
      hamming_distance += 1 unless firstDNA[i] == secondDNA[i]
    end

    hamming_distance
  end
end
