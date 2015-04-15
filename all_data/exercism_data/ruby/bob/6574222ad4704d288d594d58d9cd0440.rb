class Bob
  def hey(message)
    response = ResponseFactory.create(message.to_str)
    response.message
  end

end

class ShoutResponse
  def message
	'Woah, chill out!'
  end
end

class QuestionResponse
  def message
	'Sure.'
  end  
end

class SilenceResponse
  def message
	'Fine. Be that way!'
  end  
end

class CarelessResponse
  def message
	'Whatever.'
  end
end

class ResponseFactory
	def self.create(phrase)	        						
		if (phrase.strip.empty?) #i hate that I have to strip here is there a better way?	phrase.whiteSpace?		
			return SilenceResponse.new
		elsif (phrase == phrase.upcase)			
			return ShoutResponse.new
		elsif (phrase.end_with?('?'))			
			return QuestionResponse.new
		else
			return CarelessResponse.new
		end			
	end
end
