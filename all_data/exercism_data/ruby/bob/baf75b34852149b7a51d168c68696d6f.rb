class Bob
  def hey sentence, *another
    @sentence = sentence || ''
    return "One.at.a.time." if another.any?     # Multiple people talking
    return "Fine. Be that way!" if is_silent? 
    return "Woah, chill out!" if is_shouting?
    return "Sure." if is_question?
    "Whatever."
  end

  private
    def is_shouting?
      return true if @sentence.upcase == @sentence
    end

    def is_question?
      return true if @sentence[-1] == "?" 
    end

    def is_silent?
      return true if @sentence.empty?
    end
end
