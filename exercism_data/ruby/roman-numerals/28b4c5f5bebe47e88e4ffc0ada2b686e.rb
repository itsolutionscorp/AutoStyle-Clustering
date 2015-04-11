class Fixnum
	def to_roman
		mapping = {
			0 => ['I', 'V'],
			1 => ['X', 'L'],
			2 => ['C', 'D'],
			3 => ['M'],
		}

		return self.to_s.reverse.chars.map.with_index do |n, i|
			n = n.to_i

			if n <= 3
				map[i][0] * n
			elsif n == 4
				map[i][0] + map[i][1]
			elsif n == 5
				map[i][1]
			elsif n <= 8
				map[i][1] + map[i][0] * (n - 5)
			else
				map[i][0] * (n - 8) + map[i + 1][0]
			end
		end.reverse.join
	end
end
