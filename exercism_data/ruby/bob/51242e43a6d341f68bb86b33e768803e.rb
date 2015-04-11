class Bob

  def talking_forcefully? speech
    speech.end_with? "!" or speech.include? "YOU"
  end

  def hey(speech)
    if talking_forcefully? speech and not speech.match /'/
      'Woah, chill out!'
    elsif speech.end_with? "?"
      'Sure.'
    elsif speech.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
