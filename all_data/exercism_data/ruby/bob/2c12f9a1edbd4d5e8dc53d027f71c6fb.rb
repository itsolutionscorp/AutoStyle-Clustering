class Bob
  def hey text
    statement = Statement.new(text)
    case 
    when text.nil? || statement.silence?
      "Fine. Be that way!"
    when statement.shouting?
      "Woah, chill out!"
    when statement.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

Statement = Struct.new(:text) do
  def silence?
    text.empty?
  end
  
  def question?
    text.end_with? "?"
  end

  def shouting?
    text.upcase == text
  end
end
