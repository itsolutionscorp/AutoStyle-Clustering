class Bob

  def hey(message)
    if silence?(message)
      "Fine. Be that way."
    elsif has_period?(message)
      "Whatever."
    elsif has_question?(message)
      "Sure."
    elsif has_exclamation?(message) || all_caps?(message)
      "Woah, chill out!"
    end
  end

  private

    def has_question?(message)
      message.end_with?("?")
    end

    def has_exclamation?(message)
     message.end_with?("!")
    end

    def has_period?(message)
      message.end_with?(".") || message.match(/let's/i)
    end

    def silence?(message)
      message.nil? || message.length == 0
    end

    def all_caps?(message)
      message.match(/[A-Z]+/)
    end
end
