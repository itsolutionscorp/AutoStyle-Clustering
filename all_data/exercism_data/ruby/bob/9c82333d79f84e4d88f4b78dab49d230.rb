class Bob
  def hey(text)
    text = text.to_s.strip
    return 'Fine. Be that way!' if text.empty?
    return 'Woah, chill out!' if text == text.upcase
    return 'Sure.' if text.end_with?("?")
    return 'Whatever.'
  end
end
