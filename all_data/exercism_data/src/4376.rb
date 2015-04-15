class Hamming
  def compute(strandA, strandB)
    hamming_distance = 0

    length = [strandA.length, strandB.length].min

    length.times do |index|
      hamming_distance += 1 if strandA[index] != strandB[index]
    end
    hamming_distance
  end
end
