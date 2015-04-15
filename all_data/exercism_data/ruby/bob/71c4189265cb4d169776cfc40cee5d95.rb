class Bob
  RESPONSES = {
    :silence => "Fine. Be that way!",
    :shouty => "Woah, chill out!",
    :question => "Sure.",
    :fallback => "Whatever."
  }

  def hey(words)
    types.each do |type|
      response = handle(type, words)
      return response if response
    end
  end

  private


  def types
    RESPONSES.keys
  end

  def handle(type, words)
    RESPONSES[type] if send("#{type}?", words)
  end

  def silence?(words)
    words.nil? || words.empty?
  end

  def question?(words)
    words.end_with? ??
  end

  def shouty?(words)
    words.upcase == words
  end

  def fallback?(words)
    true
  end
end
