class Hamming
	def compute(a, b)
 		ham_dist = 0
    [a.length, b.length].min.times do |i|
      ham_dist += 1 unless a[i] == b[i]
    end

    ham_dist
	end
end
