class Bob

  def hey(message)
    if shouting?(message)
      "Woah, chill out!"
  	elsif question?(message)
  		"Sure."
    elsif empty_message?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

    def question?(message)
      message.end_with?("?")
    end

    def shouting?(message)
      message.strip.length != 0 && message.eql?(message.upcase)
    end

    def empty_message?(message)
      message.nil? || message.strip.empty? || message.start_with?("{")
    end


end
