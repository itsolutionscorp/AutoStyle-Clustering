class Bob
  def hey(words)
    reply_to speech(words)
  end

  private

  def reply_to(speech)
    if speech.shouting?
      'Woah, chill out!' 
    elsif speech.question?
      'Sure.'
    elsif speech.silence?
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end

  def speech(words)
    Speech.new(words)
  end
end

Speech = Struct.new(:words) do 
  def shouting?
    silence? ? false : words == words.upcase
  end

  def question?
    silence? ? false : words.end_with?('?')
  end

  def silence?
    words.nil? || words.empty?
  end
end
