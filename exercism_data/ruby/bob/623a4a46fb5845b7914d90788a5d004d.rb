class Bob

  def hey(speech)
    return 'Fine. Be that way.' if speech.nil? || speech.empty?
    if speech =~ /\?$/
      "Sure."
    elsif speech.upcase == speech #speech =~ /^[^a-z]*$/
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
