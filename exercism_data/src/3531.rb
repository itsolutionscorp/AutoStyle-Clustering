class Hamming
  def compute(sequence1, sequence2)
    #We compare until the last strand of the shortest sequence.
    if sequence1.length < sequence2.length
      last_index = sequence1.length - 1
    else
      last_index = sequence2.length - 1
    end

    hamming_distance = 0

    (0..last_index).each do |index|
      if sequence1[index] != sequence2[index]
        hamming_distance += 1
      end
    end
    hamming_distance
  end
end