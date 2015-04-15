class ETL
	def ETL.transform(old)
		expected = {}
		old.keys.each do |x|
			value = old[x]
			value.each do |y|
				expected[y.downcase] = x
			end
		end
		return expected
	end
end
