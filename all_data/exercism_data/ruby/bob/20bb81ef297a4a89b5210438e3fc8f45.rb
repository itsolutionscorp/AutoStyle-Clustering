class Bob
	
	def hey adressing_bob
		if adressing_bob.silence?
			responds_to_silence
		else
			thinking(adressing_bob)
		end
	end	

	def thinking(adressing_bob)
		
		if adressing_bob.question?
			return responds_to_a_question(adressing_bob)
		end

		if adressing_bob.exclamation?
			return responds_to_exclamation(adressing_bob)
		end

		if adressing_bob.numeric?
			return responds_to_numbers
		end
		
		responds_to_everything_else(adressing_bob)		
	end

	def responds_to_everything_else(adressing_bob)
		if (adressing_bob == adressing_bob.upcase)
			Response::CHILL
		else	
			Response::WHATEVER
		end
	end

	def responds_to_a_question(adressing_bob)
		inspect_question = adressing_bob.delete(adressing_bob.chars.last)
		if (inspect_question == inspect_question.upcase && !inspect_question.numeric?)
			Response::CHILL
		else
			Response::SURE
		end
	end

	def responds_to_exclamation(adressing_bob)
		if (adressing_bob == adressing_bob.upcase)
			Response::CHILL
		else
			Response::WHATEVER
		end
	end

	def responds_to_numbers
		Response::WHATEVER
	end

	def responds_to_silence
		Response::FINE
	end

end

class Response
  FINE = 'Fine. Be that way!'
  CHILL = 'Woah, chill out!'
  WHATEVER = 'Whatever.'
  SURE = 'Sure.'
end

class String

	def numeric?
		Float(self.chars.last) != nil rescue false
	end

  def question?
  	self.chars.last == "?"
  end

  def exclamation?
  	self.chars.last == "!"
  end

  def silence?
		self.empty? || self.chars.count("\s") == self.chars.count
	end

end
