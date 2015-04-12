class Hamming
  def compute(sequence_1, sequence_2)
    strand_error = 0
    min_length = [sequence_1.length, sequence_2.length].min
    min_length.times do |index|
      strand_error += 1 if sequence_1[index] != sequence_2[index]
    end
    strand_error
  end
end
