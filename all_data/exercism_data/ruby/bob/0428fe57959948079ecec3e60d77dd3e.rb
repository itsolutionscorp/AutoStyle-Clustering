class Bob
  def hey message
    if is_silence message
      "Fine. Be that way."
    elsif is_shouting message
      "Woah, chill out!"
    elsif is_question message
      "Sure."
    else
      "Whatever."
    end
  end

  protected
    def is_silence message
      message.nil? || message.empty?
    end

    def is_question message
      message[-1] == "?"
    end

    def is_shouting message
      message.upcase!.nil?
    end
end
