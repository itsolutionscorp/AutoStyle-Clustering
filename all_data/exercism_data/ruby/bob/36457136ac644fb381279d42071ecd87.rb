module ClassifyString

	def is_loud?
		self.upcase!.nil? and 
		self.match(/[A-Z]+/)
	end

	def is_silent?
		self.strip ==''
	end

	def is_question?
		self.end_with?('?')
	end

end

class String
	include ClassifyString
end

class Bob
	def hey statement=''
		respond_to statement
	end

	private
	def respond_to what_is_being_said
		response = 'Whatever.'
		response = 'Sure.' if what_is_being_said.is_question?
		response = 'Whoa, chill out!' if what_is_being_said.is_loud?
		response = 'Fine. Be that way!' if what_is_being_said.is_silent?
		return response
	end
end
