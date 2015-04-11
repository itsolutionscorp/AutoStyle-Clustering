class Bob
	def hey(what_he_said)
		raise 'nothing said' if what_he_said.nil?
		what_he_said = Phrase.new(what_he_said)
		case 
		when what_he_said.blank?
			'Fine. Be that way!'
		when what_he_said.upcase?
			'Woah, chill out!'
		when what_he_said.question?
			'Sure.'
		else
			'Whatever.'
		end
	end
end

class Phrase
	attr_accessor :phrase

	def initialize(str)
		@phrase = str
	end

	def blank?	
		phrase.strip.empty?
	end

	def upcase?
		phrase =~ /[A-Z]/ && phrase == phrase.upcase
	end

	def question?
		phrase.end_with? '?'
	end
end
