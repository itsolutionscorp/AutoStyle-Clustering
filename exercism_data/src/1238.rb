class Hamming
  def compute(sequence1, sequence2)
    hamming_distance = 0
    last_index = [sequence1.length, sequence2.length].min
    (0...last_index).each do |index|
      if sequence1[index] != sequence2[index]
        hamming_distance += 1
      end
    end
    hamming_distance
  end
end
