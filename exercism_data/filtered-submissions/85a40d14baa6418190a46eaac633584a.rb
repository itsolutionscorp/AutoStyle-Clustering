class Hamming
	def compute(str_a, str_b)
    sequence_a = str_a.chars
    sequence_b = str_b.chars
    sequence_range = sequence_b.length
    sequence_a.zip(sequence_b).first(sequence_range).count { |a,b| a != b }
	end
end
