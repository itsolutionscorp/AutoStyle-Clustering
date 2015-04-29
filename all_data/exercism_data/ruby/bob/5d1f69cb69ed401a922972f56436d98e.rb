class Bob

  def hey(sentence)

  	@sentence = sentence

  	if yelled_at?
  	  'Woah, chill out!'
  	elsif asked_question?
  	  'Sure.'
  	elsif no_response?
  	  'Fine. Be that way!'
  	else
  	  'Whatever.'
  	end
  end

  private

    def yelled_at?
  	  @sentence == @sentence.upcase && @sentence != @sentence.downcase
    end

    def asked_question?
      @sentence.end_with?('?')
    end

    def no_response?
      @sentence.strip.empty?
    end
end
