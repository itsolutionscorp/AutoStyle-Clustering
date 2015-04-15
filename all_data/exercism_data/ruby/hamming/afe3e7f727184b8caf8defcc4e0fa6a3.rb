class Hamming

	def self.compute(string_one, string_two)
		length = self.string_length(string_one, string_two)
		count = 0
		length.times do |x|
			count += 1 if string_one[x] != string_two[x]
		end
		count
	end

	private

	def self.string_length(string_one, string_two)
		string_two.length <= string_one.length ? string_two.length : string_one.length
	end
end
