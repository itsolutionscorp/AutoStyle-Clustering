class Bob
  def hey(words)
    handle_silence(words) ||
      handle_shouty(words) ||
      handle_question(words) ||
      "Whatever."
  end

  private

  def handle_silence(words)
    "Fine. Be that way!" unless spoken?(words)
  end

  def handle_shouty(words)
    "Woah, chill out!" if shouty?(words)
  end

  def handle_question(words)
    "Sure." if question?(words)
  end

  def spoken?(words)
    words && !words.empty?
  end

  def question?(words)
    words.end_with? ??
  end

  def shouty?(words)
    words.upcase == words
  end
end
