class Hamming
  def compute(strand1, strand2)
    shortest_length = [strand1.length, strand2.length].min
    (0...shortest_length).inject(0){ |result, i| result + ( strand1[i] != strand2[i] ? 1 : 0) }
  end
end
