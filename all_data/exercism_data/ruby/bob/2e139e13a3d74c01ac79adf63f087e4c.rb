class Bob

  def hey(message)
    if silence?(message)
      "Fine. Be that way."
    elsif has_period?(message)
      "Whatever."
    elsif has_question?(message)
      "Sure."
    elsif has_exclamation?(message) || numbers?(message) || all_caps?(message)
      "Woah, chill out!"
    #else
    #  "Whatever."
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

    def numbers?(message)
      message.match(/d/)
    end

    def all_caps?(message)
      message.match(/\w+/i)
    end
end
