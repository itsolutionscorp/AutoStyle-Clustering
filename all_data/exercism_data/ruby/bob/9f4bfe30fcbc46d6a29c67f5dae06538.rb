class Bob
  def hey(statement)
    @statement = statement
    if silence?
      "Fine. Be that way!"
    elsif shouting_in_words?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  attr_reader :statement

  def silence?
    statement !~ /\w/
  end

  def shouting_in_words?
    statement == statement.upcase && statement.match(/[a-zA-Z]/)
  end

  def question?
    statement.tr("\n", '') =~ /\?$/
  end
end
