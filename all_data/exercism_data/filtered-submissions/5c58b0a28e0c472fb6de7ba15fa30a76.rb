def compute(a, b)
		hamming_distance_array = Array.new

		a.split(//).zip(b.split(//)).each do |x|
			if x.uniq.length > 1 && !(a.eql? b)
				hamming_distance_array << x.join(',')
			else
				0
			end
		end

		hamming_distance_array.length
	end