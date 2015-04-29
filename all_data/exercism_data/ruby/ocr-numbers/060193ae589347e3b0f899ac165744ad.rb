class OCR

	@@NUMBERS = {
		"0" => " _ \n| |\n|_|\n   ",
		"1" => "   \n  |\n  |\n   ",
		"2" => " _ \n _|\n|_ \n   ",
		"3" => " _ \n _|\n _|\n   ",
		"4" => "   \n|_|\n  |\n   ",
		"5" => " _ \n|_ \n _|\n   ",
		"6" => " _ \n|_ \n|_|\n   ",
		"7" => " _ \n  |\n  |\n   ",
		"8" => " _ \n|_|\n|_|\n   ",
		"9" => " _ \n|_|\n _|\n   "
	}

	@@ROWS_PER_DIGIT = 4
	@@COLS_PER_DIGIT = 3

	def initialize(image)
		@image = image
	end

	def convert
		digits(@image).map do |line|
			line.map do |digit|
				n, pic = @@NUMBERS.find { |n, pic| digit == pic }	
				n || "?"
			end.join
		end.join(",")
	end

	private

		def digits(image)
			image = image.split("\n")

			image.each_slice(@@ROWS_PER_DIGIT).map do |line|
				digits_in_line(line)
			end
		end

		def digits_in_line(line)
			scans = line.map do |scan|
				scan.chars.each_slice(@@COLS_PER_DIGIT).map(&:join)
			end
			
			scans.first.each_index.map do |i|
				scans.map { |scan| scan[i] }.join("\n") 
			end
		end
	
end
