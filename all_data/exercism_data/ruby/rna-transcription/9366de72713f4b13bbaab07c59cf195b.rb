class Complement
	def self.of_dna(a)
		b = String.new
		a.length.times do |n|
			if a[n] == 'G'
				b << 'C'
			end
			if a[n] == 'C'
				b << 'G'
			end
			if a[n] == 'T'
				b << 'A'
			end
			if a[n] == 'A'
				b << 'U'
			end
		end
		return b
	end

	def self.of_rna(a)
		b = String.new
		a.length.times do |n|
			if a[n] == 'C'
				b << 'G'
			end
			if a[n] == 'G'
				b << 'C'
			end
			if a[n] == 'A'
				b << 'T'
			end
			if a[n] == 'U'
				b << 'A'
			end
		end
		return b
	end
end
