class WordProblem

	def initialize(question)
		@question = question
	end
	
	def calculate(md)
		if 		/plus/.match(md[2]) then
				result = md[1].to_f + md[3].to_f
		elsif 	/minus/.match(md[2]) then
				result = md[1].to_f - md[3].to_f
		elsif 	/divided by/.match(md[2]) then
				result = md[1].to_f / md[3].to_f
		elsif 	/multiplied by/.match(md[2]) then
				result = md[1].to_f * md[3].to_f
		end 
		result
	end
	
	def calculate_on(md, r)
		if 		/plus/.match(md[2]) then
				result = r + md[3].to_f
		elsif 	/minus/.match(md[2]) then
				result = r - md[3].to_f
		elsif 	/divided by/.match(md[2]) then
				result = r / md[3].to_f
		elsif 	/multiplied by/.match(md[2]) then
				result = r * md[3].to_f
		else 	raise ArgumentError.new('Too advanced!')
		end 
		result
	end

	def answer 
		if /^What is ([-]*\d+) ([^\d-]+)([-]*\d+)([^\d-]+[-]*\d+)*\?$/.match(@question)
			md = /^What is ([-]*\d+) ([^\d-]+)([-]*\d+)[ ]*(.*)\?$/.match(@question)			
			result = calculate(md)
			
			if md[4] != nil
				if 	md_new = /()([^\d-]+)([-]*\d+)[ ]*(.*)$/.match(md[4])
					result = calculate_on(md_new, result)
				end
			end
		else raise ArgumentError.new('This is not a possible word problem.')
		end
		result
	end
	
end
