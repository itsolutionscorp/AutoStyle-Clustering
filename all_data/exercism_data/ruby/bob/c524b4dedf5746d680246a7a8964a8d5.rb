class Bob

  def hey(message)
    if silence?(message)
      "Fine. Be that way."
    elsif has_period?(message)
      "Whatever."
    elsif has_question?(message)
      "Sure."
    else
      "Woah, chill out!"
    end
  end

  private

    def has_question?(message)
      message.end_with?("?")
    end

    def has_period?(message)
      message.end_with?(".") || message.match(/let's/i)
    end

    def silence?(message)
      message.nil? || message.empty?
    end

end
