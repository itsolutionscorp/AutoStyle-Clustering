class Bob

  def hey(speech)
    return 'Fine. Be that way.' if speech.nil? || speech.empty?
    if asking? speech
      "Sure."
    elsif yelling? speech
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def asking?(speech)
    speech.end_with?('?')
  end

  def yelling?(speech)
    speech.upcase == speech
  end
end
