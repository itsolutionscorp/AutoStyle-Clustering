require 'active_support/all'

class Bob
	def initialize
	end

	def hey(some_word)
		if some_word.blank?
			'Fine. Be that way!'
		elsif !!!some_word.match(/[[:lower:]]/)
			'Woah, chill out!'
		elsif some_word[-1] == '?'
			'Sure.'
		else
			'Whatever.'
		end

	end
end
