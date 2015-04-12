class Hamming
  
  def compute(sequence_a, sequence_b)
    (0...[sequence_a.length, sequence_b.length].min).count do |i|
      sequence_a[i] != sequence_b[i]
    end
  end
  
end
