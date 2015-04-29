class Raindrops


	def self.convert(test)
		result = three_test(test).to_s + five_test(test).to_s + seven_test(test).to_s
		if result[0] == "P"
			result
		else
			test.to_s
		end
	end


	private

	def self.three_test(input)
		if input % 3 == 0
			"Pling"
		end
	end

	def self.five_test(input)
		if input % 5 == 0
			"Plang"
		end
	end

	def self.seven_test(input)
		if input % 7 == 0
			"Plong"
		end
	end
end
