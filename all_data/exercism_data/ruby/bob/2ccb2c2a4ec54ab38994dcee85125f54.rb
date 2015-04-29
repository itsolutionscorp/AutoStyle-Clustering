class Bob

  def hey(speech)
    if nothing? speech
      'Fine. Be that way.'
    elsif asking? speech
      "Sure."
    elsif yelling? speech
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def nothing?(speech)
    speech.nil? || speech.empty?
  end

  def asking?(speech)
    speech.end_with?('?')
  end

  def yelling?(speech)
    speech.upcase == speech
  end
end
