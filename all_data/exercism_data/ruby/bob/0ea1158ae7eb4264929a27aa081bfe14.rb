class Bob
  
  def hey text
    text = Text.new(text)
    case text
    when :empty?.to_proc
      "Fine. Be that way."
    when :all_caps?.to_proc
      "Woah, chill out!"
    when :question?.to_proc
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
