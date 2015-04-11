class Grains
	def square pos
		(1..pos).reduce(0) do |acc, n|
			next 1 if acc == 0
			next acc * 2
		end
	end


	def total
		(1..64).reduce(0) { |acc, n| acc + self.square(n) }
	end
end
