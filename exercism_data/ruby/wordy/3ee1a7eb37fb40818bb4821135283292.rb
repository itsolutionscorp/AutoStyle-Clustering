PATTERN = /-?\d+|plus|minus|multiplied by|divided by/
OPS = 
{
	'plus' => :+, 
	'minus' => :-, 
	'multiplied by' => :*,
	'divided by' => :/ 
}

class WordProblem
	attr_reader :answer
	
	def initialize(problem)
		tokens = problem.scan(PATTERN)
		raise(ArgumentError) if tokens.size < 3
		
		@answer = tokens.shift.to_i
		while !tokens.empty?
			op = tokens.shift
			num = tokens.shift.to_i
			@answer = @answer.send(OPS[op], num)
		end
	end
end
