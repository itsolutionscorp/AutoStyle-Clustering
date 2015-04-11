require 'set'

class PigLatin
	@@vowels = ['a', 'e', 'i', 'o', 'u'].to_set()
	
	def self.translate(text)
		text.split.collect do |word|
			front, back = '', word
			
			if word[0] == 'y' && !@@vowels.include?(word[1])
				# leave it alone
			elsif !@@vowels.include?(word.chars.first) 
				i = word.chars.index{|c| @@vowels.include?(c)}
				front, back = word[0...i], word[i..-1]
				if front[-1] == 'q' && back[0] == 'u'
					front += 'u'
					back = back[1..-1]
				end
			end
			
			 # this line is a hack to get the xray one to work...
			 front, back = '', word if front == 'xr'
			
			"#{back}#{front}ay"
		end.join(' ')
	end
end
