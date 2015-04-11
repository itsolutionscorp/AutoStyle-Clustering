class Bob
  def hey(words)
    query = check_words(words)
    respond(query)
  end

  def check_words(words)
    if silent?(words)
      :silent
    elsif words.upcase == words
      :shout
    elsif words[-1] == "?"
      :question
    else
      :statement
    end
  end

  def respond(query)
    case query
    when :silent
      "Fine. Be that way!"
    when :shout
      "Woah, chill out!"
    when :question
      "Sure."
    when :statement
      "Whatever."
    end
  end

  def silent?(words)
    words.nil? || words.strip.empty?
  end
end
