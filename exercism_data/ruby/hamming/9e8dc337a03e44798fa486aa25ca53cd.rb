class Hamming
	def self.compute seq_a, seq_b
    count = 0
    n = [seq_a.size,seq_b.size].min
    (0...n).each do |i|
      count +=1 if seq_a[i]!=seq_b[i]
    end
    count
  end
end
