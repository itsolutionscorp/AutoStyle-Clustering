class Bob

  def hey text
    text = Text.new(text)
    case 
    when text.empty?
      "Fine. Be that way!"
    when text.all_caps?
      "Woah, chill out!"
    when text.question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Text < Struct.new(:text)
    def empty?
      text.nil? || text == ""
    end

    def question?
      text.chars.last == "?"
    end

    def all_caps?
      text.upcase == text
    end
  end

end
