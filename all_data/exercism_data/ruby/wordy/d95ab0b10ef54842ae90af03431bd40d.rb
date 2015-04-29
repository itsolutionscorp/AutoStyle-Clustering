class WordProblem

	PATTERN = /-?\d+|plus|minus|multiplied by|divided by/
	OPS = { 'plus' => :+, 
					'minus' => :-, 
					'multiplied by' => :*,
					'divided by' => :/ }

	def initialize(question)
		@tokens = question.scan(PATTERN)
	end
	
	def answer
		raise ArgumentError if @tokens.length < 3
		operate(@tokens)
	end
	
private
	def operate(tokens)
		return tokens.first.to_i if tokens.length == 1
		arg1, op, arg2 = tokens.shift(3)
		tokens.unshift([arg1.to_i, arg2.to_i].reduce(OPS[op]).to_s)
		operate(tokens)
	end
end
