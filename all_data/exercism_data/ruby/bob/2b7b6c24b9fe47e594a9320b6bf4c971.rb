class Bob
  def hey(statement)
    respond_to Phrase.new(statement)
  end

  def respond_to(phrase)
    case
    when phrase.silence?
      then 'Fine. Be that way!'
    when phrase.yelling?
      then "Woah, chill out!"
    when phrase.question?
      then "Sure."
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
