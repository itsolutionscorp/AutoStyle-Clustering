class Grains

	def square(n)
		(2..n).reduce(1) { |s,i| s = 2*s }
	end

	def total
		(1..64).reduce(0){ |total,i| total += Grains.new.square(i) }
	end

end
