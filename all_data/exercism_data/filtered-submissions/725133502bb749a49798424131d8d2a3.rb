def compute(a,b)



		shortest_length = [a.length, b.length].min

		(0...shortest_length).reduce(0) { |hamm,i| a[i] == b[i] ? hamm : hamm + 1 }

	end