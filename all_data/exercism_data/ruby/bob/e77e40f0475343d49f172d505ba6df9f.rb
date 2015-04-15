class Bob
  def hey sentence
    @sentence = sentence || ''
    return "Fine. Be that way!" if @sentence.empty?
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
end
