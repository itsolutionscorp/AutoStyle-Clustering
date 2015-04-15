class OCR
	
	DIGITS = { 	[" _ ", "| |", "|_|", "   "] => '0',
							["   ", "  |", "  |", "   "] => '1',
							[" _ ", " _|", "|_ ", "   "] => '2',
							[" _ ", " _|", " _|", "   "] => '3',
							["   ", "|_|", "  |", "   "] => '4',
							[" _ ", "|_ ", " _|", "   "] => '5',
							[" _ ", "|_ ", "|_|", "   "] => '6',
							[" _ ", "  |", "  |", "   "] => '7',
							[" _ ", "|_|", "|_|", "   "] => '8',
							[" _ ", "|_|", " _|", "   "] => '9' }

	def initialize(text)
		@digits = text.split("\n").each_slice(4).map do |block|
			block.map { |row| row.scan(/.{3}/) }.transpose
		end
	end
	
	def convert
		@digits.inject([]) do |numbers, block|
			numbers << block.inject("") do |number, digit|
				number + (DIGITS[digit] || '?')
			end
		end.join(",")
	end
end
