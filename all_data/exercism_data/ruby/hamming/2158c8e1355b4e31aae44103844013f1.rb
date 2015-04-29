class Hamming
	def self.compute(s, t)
		s.chars.zip(t.chars).count { |s_item, b_item| s_item != b_item && b_item != nil }
	end
end
