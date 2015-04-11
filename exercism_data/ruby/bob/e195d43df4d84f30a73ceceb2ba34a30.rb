class Bob

  def hey(message)
    if all_caps?(message)
      "Woah, chill out!"
  	elsif is_question?(message)
  		"Sure."
    elsif nothing_to_say?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

    def is_question?(message)
      message.end_with?("?")
    end

    def all_caps?(message)
        message.strip.length != 0 && message.eql?(message.upcase)
    end

    def nothing_to_say?(message)
      message.nil? || message.strip.length == 0 || message.start_with?("{")
    end


end
