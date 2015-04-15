class WordProblem

	NUMBER_SCAN = /((?:-)?\d+)/

	OPERATIONS = {
		"plus" 			=> ->(a, b) { a + b },
		"minus" 		=> ->(a, b) { a - b },
		"multiplied by" => ->(a, b) { a * b },
		"divided by" 	=> ->(a, b) { a / b }
	}

	def initialize(problem)
		@problem = problem
		@pieces = decompose problem
		validate_pieces
	end

	def answer
		@pieces.drop(1).each_slice(2).reduce(@pieces[0]) do |sum, (operator, operand)|
			OPERATIONS[operator].call(sum, operand)
		end
	end

	private

		def decompose(problem)
			problem.split(NUMBER_SCAN)[1..-2].each_with_index.map do |e, i|
				i % 2 == 1 ? e.strip : e.to_i
			end
		end

		def validate_pieces
			raise ArgumentError unless @pieces.length >= 3 && @pieces.all? do |piece|
				piece.is_a?(Integer) || OPERATIONS.has_key?(piece)
			end
		end
	
end
