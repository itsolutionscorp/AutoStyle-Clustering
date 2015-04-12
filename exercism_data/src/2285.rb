class Hamming
    def compute(seq_a, seq_b)
    	len = seq_a.size
    	diff = 0
        0...len.each do |i|
        	diff += 1 unless seq_a[i] == seq_b[i]
        end
    end
end
