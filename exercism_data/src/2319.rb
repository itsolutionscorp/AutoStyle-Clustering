class Hamming
  def compute(left, right)
    hamming_distance = 0
    min_length_of_sequence = [left.length, right.length].min
    min_length_of_sequence.times do |index|
      unless left[index] == right[index]
        hamming_distance += 1
      end
    end
    hamming_distance
  end
end
