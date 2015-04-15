class Bob
  def hey(text)
    return 'Fine. Be that way!' if text.strip.empty?
    return 'Woah, chill out!' if text == text.upcase
    return 'Sure.' if text[-1] == '?'
    return 'Whatever.'
  end
end
