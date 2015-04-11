class Hamming
  def self.compute(strand1, strand2)
    counter = 0
    for position in 0..strand1.length - 1
      counter += (strand2[position].nil? || strand1[position] == strand2[position]) ? 0 : 1
    end
    counter
  end
end
