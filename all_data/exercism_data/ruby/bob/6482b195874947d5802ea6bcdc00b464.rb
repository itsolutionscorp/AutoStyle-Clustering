class Bob
  def hey sentence
    return "Fine. Be that way!" if is_silent?(sentence)
    return "Woah, chill out!" if is_shouting?(sentence)
    return "Sure." if is_question?(sentence)
    "Whatever."
  end

  private
    def is_shouting? sentence
      sentence.upcase == sentence
    end

    def is_question? sentence
      sentence.end_with? "?"
    end

    def is_silent? sentence
      sentence == nil || sentence.empty?
    end
end
