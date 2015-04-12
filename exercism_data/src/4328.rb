class Hamming
  def compute(sequence1, sequence2)
    compute_length = [sequence1.length, sequence2.length].min
    sequence1, sequence2 = sequence1[0, compute_length], sequence2[0, compute_length]
    sequence1.bytes.zip(sequence2.bytes).count do | byte1, byte2 |
      byte1 != byte2
    end
  end
end
