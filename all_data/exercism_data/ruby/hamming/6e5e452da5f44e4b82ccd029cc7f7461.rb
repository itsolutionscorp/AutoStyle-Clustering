class Hamming
  def self.compute(sequence_a, sequence_b)
    a = sequence_a.split(//)
    b = sequence_b.split(//)
    distance = 0
    a.each_with_index do |acid, i|
      distance += 1 if a[i] != b[i]
    end
    distance
  end
end
