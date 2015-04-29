class Bob
  def hey(speech)
    respond_to(speech)
  end

  private 

  def respond_to(speech)
    perceived_speech = BobsPerceptionOfSpeech.new(speech)
    if perceived_speech.silence?
      'Fine. Be that way.'
    elsif perceived_speech.statement? || perceived_speech.forceful?
      'Whatever.'
    elsif perceived_speech.shouting?
      'Woah, chill out!'
    elsif perceived_speech.question?
      'Sure.'
    end
  end
end

class BobsPerceptionOfSpeech < String
  def initialize(*args)
    super
    self.strip!
  end

  def statement?
    self.last == '.'
  end

  def forceful?
    (self.last == '!') && (self != self.upcase)
  end

  def shouting?
    self == self.upcase
  end

  def question?
    self.last == '?'
  end

  def silence?
    self == ''
  end

  def last
    self[self.length-1]
  end
end
