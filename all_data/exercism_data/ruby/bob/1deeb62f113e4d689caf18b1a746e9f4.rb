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

class Speech < String
  def initialize(text)
    super text.to_s.strip
  end

  def shouting?
    !empty? && (self == self.upcase)
  end

  def question?
    end_with?('?')
  end

  def silence?
    empty?
  end
end
