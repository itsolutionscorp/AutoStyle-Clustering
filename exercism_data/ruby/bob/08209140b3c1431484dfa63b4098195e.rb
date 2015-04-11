class Bob
  def hey speech
    speech_act = SpeechAct.new(speech)
    return 'Fine. Be that way!' if speech_act.silence?
    return 'Woah, chill out!' if speech_act.shout?
    return 'Sure.' if speech_act.question?
    'Whatever.'
  end
end

class SpeechAct
  def initialize speech
    @speech = String(speech).strip
  end

  def question?
    @speech.end_with?('?')
  end

  def shout?
    @speech == @speech.upcase
  end

  def silence?
    @speech.empty?
  end
end
