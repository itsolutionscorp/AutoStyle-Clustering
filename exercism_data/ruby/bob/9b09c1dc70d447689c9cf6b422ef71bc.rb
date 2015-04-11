class Bob
    def hey(message)
      response = BobResponse.new(message)
      return response.get_response      
    end
	end
  
 class BobResponse
  def initialize(message)
    @message = message
  end
  def get_response()
    if (is_silence)
        "Fine. Be that way!"
      elsif (is_yell)
        "Woah, chill out!"
      elsif (is_question)
        "Sure."
      else
        "Whatever."
      end
  end
	def is_silence
		@message.strip.empty?
	end
	def is_yell
		@message.upcase == @message
	end
	def is_question
		@message[-1] == "?"
	end
end
