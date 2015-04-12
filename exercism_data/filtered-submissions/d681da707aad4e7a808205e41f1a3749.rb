class Hamming
    def compute(seq_a, seq_b)
    	len = [seq_a.size, seq_b.size].min
    	diff = 0
        (0...len).each do |i|
        	diff += 1 unless seq_a[i] == seq_b[i]
        end
        diff
    end
end
