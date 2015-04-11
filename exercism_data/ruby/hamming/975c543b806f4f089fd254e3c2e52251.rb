class Hamming
	def self.compute(first, second)
		distance = 0;
		shorter, longer = [first.chars, second.chars].sort { |x, y| x.length <=> y.length }
		shorter.each_with_index do |ch, i|
			distance += 1 if longer[i] != ch
		end
		distance
	end
end
