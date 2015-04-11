class Bob
  def hey sentence
    if silent?(sentence)
      "Fine. Be that way!"
    elsif shouting?(sentence)
      "Woah, chill out!" 
    elsif question?(sentence)
      "Sure." 
    else 
      "Whatever."
    end 
  end

  private
    def shouting? sentence
      sentence.upcase == sentence
    end

    def question? sentence
      sentence.end_with? "?"
    end

    def silent? sentence
      sentence.to_s.empty?
    end
end
