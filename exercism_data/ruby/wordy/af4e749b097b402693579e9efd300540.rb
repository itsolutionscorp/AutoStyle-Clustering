class WordProblem
	attr_reader :answer
	
	def initialize(problem)
		raise(ArgumentError) unless match = problem.match(/^What is (-?[\d]+) (.*)\?$/)
		@answer = match[1].to_i
		problem = match[2].strip
		
		while match = problem.match(/^(plus|minus|multiplied by|divided by) (-?[\d]+)(.*)$/)
			op = match[1]
			num = match[2].to_i
			problem = match[3].strip
			
			case op
				when 'plus'
					@answer += num
				when 'minus'
					@answer -= num
				when 'multiplied by'
					@answer *= num
				when 'divided by'
					@answer /= num
			end
		end
		
		raise(ArgumentError) unless problem.empty?
	end
end
