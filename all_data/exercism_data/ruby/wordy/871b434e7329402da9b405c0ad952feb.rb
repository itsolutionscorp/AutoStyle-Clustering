class WordProblem

	NUMBER_SCAN = /(-?\d+)/

	OPERATIONS = {
		"plus" 			=> :+,
		"minus" 		=> :-,
		"multiplied by" => :*,
		"divided by" 	=> :/
	}

	def initialize(problem)
		@problem = problem
		@pieces = decompose problem
		validate_pieces
	end

	def answer
		@pieces.drop(1).each_slice(2).reduce(@pieces[0]) do |sum, (operator, operand)|
			sum.send(OPERATIONS[operator], operand)
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
