class PigLatin

	def self.translate(words)
		arr = words.scan(/\w+/)
		translation = ''
		arr.each do |word|
			cons = /^[^aeiou]*qu|^[^aeiou]+/.match(word)
			if 		/^yt|^xr|^[aeiou]/.match(word)
					translation += word + 'ay'
			else	translation += word.sub!(cons[0], '') + cons[0] + 'ay'
			end	
			translation += ' '
		end
		translation.chop!
	end

end
