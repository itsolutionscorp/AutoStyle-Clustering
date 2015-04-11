class Bob
  def hey sentence
    return "Fine. Be that way!" if is_silent?(sentence)
    return "Woah, chill out!" if is_shouting?(sentence)
    return "Sure." if is_question?(sentence)
    "Whatever."
  end

  private
    def is_shouting? sentence
      return true if sentence.upcase == sentence
    end

    def is_question? sentence
      return true if sentence[-1] == "?" 
    end

    def is_silent? sentence
      return true if sentence == nil || sentence.empty?
    end
end
