class Bob

  def hey message
    language = Language.new(message)
    case
      when language.silence?   then "Fine. Be that way!"
      when language.aggresive? then "Woah, chill out!"
      when language.question?  then "Sure."
    else
      "Whatever."
    end
  end

end

class Language

  def initialize text
    @text = text
  end

  def aggresive?
    @text.upcase == @text
  end

  def question?
    @text.end_with? "?"
  end

  def silence?
    @text.nil? || @text.empty?
  end

end
