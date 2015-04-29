class Hamming
	def self.compute(str_a, str_b)
    str_a.chars.zip(str_b.chars).first(str_b.chars.length).count { |a,b| (a != b && !b.nil?) }
	end
end
