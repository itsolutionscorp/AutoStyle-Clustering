class PigLatin
	
	VOCAL = ['a', 'e', 'i', 'o', 'u']

	class << self
		def translate(text)
			text.split(" ").map do |word|
				rotate(word, chars_to_rotate(word)) + 'ay'
			end.join(" ")
		end
		
	private
		def chars_to_rotate(word)
			return 0 if word.downcase.start_with?(*VOCAL, 'yt', 'xr')
			return 3 if word.downcase.start_with?('thr', 'sch')
			return 2 if word.downcase.start_with?('ch', 'qu', 'th')
			return 3 if word.downcase[1..2] == 'qu'
			return 1
		end
	
		def rotate(word, chars)
			word[chars..-1] + word[0...chars]
		end
	end
end
