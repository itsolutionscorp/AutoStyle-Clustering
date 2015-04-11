class Bob

  def hey(msg)
    return 'Woah, chill out!' if sounds_like_shouting?(msg)
    return 'Sure.' if sounds_like_a_question?(msg)
    return 'Fine. Be that way!' if sounds_like_silence?(msg)
    return 'Whatever.'
  end

  private

  def sounds_like_shouting?(msg)
    is_uppercase?(msg) && includes_characters?(msg)
  end

  def sounds_like_silence?(msg)
    msg.strip.empty?
  end

  def sounds_like_a_question?(msg)
    msg.end_with?("?")
  end

  def includes_characters?(text)
    text.upcase != text.downcase
  end

  def is_uppercase?(text)
    text == text.upcase
  end

end
