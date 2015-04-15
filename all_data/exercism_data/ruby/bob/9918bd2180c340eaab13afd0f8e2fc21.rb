class Bob
  def hey(statement)
    respond_to Phrase.new(statement)
  end

  def respond_to(phrase)
    if phrase.silence?
      return 'Fine. Be that way!'
    elsif phrase.yelling?
      return "Woah, chill out!"
    elsif phrase.question?
      return "Sure."
    else
      "Whatever."
    end
  end


  Phrase = Struct.new(:statement) do
    def yelling?
      statement =~ /[A-Z]/ && statement.upcase == statement
    end

    def question?
      statement.end_with?("?")
    end

    def silence?
      statement.strip.empty?
    end
  end
end
