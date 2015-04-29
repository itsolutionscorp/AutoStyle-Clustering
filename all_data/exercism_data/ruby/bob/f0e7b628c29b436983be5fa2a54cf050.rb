class Bob
  def hey(words)
    handle_silence(words) ||
    handle_shouty(words) ||
    handle_question(words) ||
    "Whatever."
  end

  private

  def handle_silence(words)
    "Fine. Be that way." unless words && !words.empty?
  end

  def handle_shouty(words)
    "Woah, chill out!" if shouty?(words)
  end

  def handle_question(words)
    "Sure." if question?(words)
  end

  def question?(words)
    words =~ /\?$/
  end

  def shouty?(words)
    letters = words.gsub(/[^a-zA-Z]/, '')
    letters =~ /^[A-Z]+$/
  end
end
