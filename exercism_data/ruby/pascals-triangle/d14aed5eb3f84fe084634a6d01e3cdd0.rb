class Triangle
	
	attr_reader :rows_up_to

	def initialize(rows)
		@rows_up_to = rows
	end
	
	def rows
		pascal = self.pascal
		pascal[0...(pascal.length - 1)]
	end
	
	def pascal(arr = [[1]], r = 1)
		trio = []
		arr[r-1].each_with_index do |n,i|
			if i == 0
				trio << 1 
			end
			if arr[r-1].length > 1 && i > 0 
				trio << arr[r-1][i-1].to_i + arr[r-1][i].to_i
			end
			if i == (arr[r-1].length - 1)
				trio << 1 
			end			
		end
		arr << trio
		return arr if r == rows_up_to
		r += 1
		self.pascal(arr, r)
	end
	
end
