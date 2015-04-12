class Hamming

	def compute(a,b)
		a,b = [a,b].sort     { a.length <=> b.length }
		(0...a.length).count { |p| a[p] != b[p]      }
	end

end
