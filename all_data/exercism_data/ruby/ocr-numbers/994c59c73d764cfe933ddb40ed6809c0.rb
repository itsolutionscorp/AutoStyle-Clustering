class OCR
	@@mapping = 
	[
		[[" ", "_", " "], ["|", " ", "|"], ["|", "_", "|"], [" ", " ", " "]], #0
		[[" ", " ", " "], [" ", " ", "|"], [" ", " ", "|"], [" ", " ", " "]], #1
		[[" ", "_", " "], [" ", "_", "|"], ["|", "_", " "], [" ", " ", " "]], #2
		[[" ", "_", " "], [" ", "_", "|"], [" ", "_", "|"], [" ", " ", " "]], #3
		[[" ", " ", " "], ["|", "_", "|"], [" ", " ", "|"], [" ", " ", " "]], #4
		[[" ", "_", " "], ["|", "_", " "], [" ", "_", "|"], [" ", " ", " "]], #5
		[[" ", "_", " "], ["|", "_", " "], ["|", "_", "|"], [" ", " ", " "]], #6
		[[" ", "_", " "], [" ", " ", "|"], [" ", " ", "|"], [" ", " ", " "]], #7
		[[" ", "_", " "], ["|", "_", "|"], ["|", "_", "|"], [" ", " ", " "]], #8
		[[" ", "_", " "], ["|", "_", "|"], [" ", "_", "|"], [" ", " ", " "]], #9
	]

	def initialize(text)
		@matrix = text.split("\n").collect{|line| line.chars}
	end

	def convert()
		@matrix.each_slice(4).collect{|line| convert_line(line)}.join(',')
	end

	private 
	
	def convert_line(line)
		# break line up into chars
		chars = []
		0.step(line[0].length - 1, 3).each do |col|
			chars << 0.upto(3).collect{|row| line[row][col...col + 3]}
		end

		# convert chars
		chars.collect{|char| convert_char(char)}.join
	end

	def convert_char(char)
		num = @@mapping.index(char)
		(num) ? num : '?'
	end
end
