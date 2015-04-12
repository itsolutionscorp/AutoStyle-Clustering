class Hamming
  def compute(strand1, strand2)
    shortest_length = [strand1.length, strand2.length].min
    (0...shortest_length).reduce(0) do |sum, i|
      sum += 1 if strand1[i] != strand2[i]
      sum
    end
  end
end
