class Utterance < String
	def blank?
		self.strip.empty?
	end

	def shouting?
		self == self.upcase
	end

	def question?
		self.end_with? '?'
	end
end

class Bob
	def hey(text)
		utterance = Utterance.new(text.to_s)
		return 'Fine. Be that way!' if utterance.blank?
		return 'Woah, chill out!' if utterance.shouting?
		return 'Sure.' if utterance.question?
		return 'Whatever.'
	end
end
