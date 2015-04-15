class Bob

  def hey(text)
    return 'Fine. Be that way.' if text.nil? || text.empty?
    return 'Woah, chill out!' if text == text.upcase
    return 'Sure.' if text.end_with?('?')
    'Whatever.'
  end

end
