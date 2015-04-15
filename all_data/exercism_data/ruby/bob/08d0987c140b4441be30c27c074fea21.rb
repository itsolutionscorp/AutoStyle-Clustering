class Bob

  def hey text
    text = Text.new(text)
    case 
    when text.empty?
      "Fine. Be that way!"
    when text.shouting?
      "Woah, chill out!"
    when text.question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Text < Struct.new(:text)
    def empty?
      text.nil? || text.empty?
    end

    def question?
      text.end_with? "?"
    end

    def shouting?
      text.upcase == text
    end
  end

end
