def compute(a, b)


		a.each_char.zip(b.each_char).count do |n1, n2|
			n2 != nil and n1 != n2
		end
	end