require 'forwardable'

class Bob

  def hey text
    text = Text.new(text)
    case 
    when text.nil? || text.silence?
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
    
    extend Forwardable
    def_delegator :text, :nil?
    def_delegator :text, :empty?, :silence?
    
    def question?
      text.end_with? "?"
    end

    def shouting?
      text.upcase == text
    end
  end

end
