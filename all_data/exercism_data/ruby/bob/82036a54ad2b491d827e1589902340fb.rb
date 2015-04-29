class Bob

	def hey( string )
		#this is me trying to become more comfortable with ruby regex
		if string.empty? || string.match(/^\s+\z/)
			return 'Fine. Be that way!'
		elsif (string.match(/[a-z] | (^([0-9]\W*)+\z)/) == nil )
			return 'Whoa, chill out!'
		elsif string.match(/\?+\z/)
			return 'Sure.'
		else
			return 'Whatever.'
		end
	end

end
