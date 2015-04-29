class Bob

  def hey(speech)
    case
    when is_silence?(speech) then 'Fine. Be that way.'
    when is_caps?(speech) then 'Woah, chill out!'
    when is_question?(speech) then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def is_silence?(speech)
    speech.to_s.length == 0
  end

  def is_caps?(speech)
    speech == speech.upcase
  end

  def is_question?(speech)
    speech =~ /\?$/
  end
end
