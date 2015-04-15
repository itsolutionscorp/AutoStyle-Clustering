class EmotionClassifier

  attr_reader :msg

  def initialize(msg)
    @msg = msg
  end

  def detect_emotion
    if silence?
      :cold
    elsif anger?
      :angry
    elsif question?
      :curious
    else
      :indifferent
    end
  end

  def silence?
    msg.strip.empty?
  end

  def anger?
    msg == msg.upcase
  end

  def question?
    msg.end_with?('?')
  end

end

class Bob

  def hey(msg)
    emotion = EmotionClassifier.new(msg).detect_emotion

    case emotion
    when :cold    then 'Fine. Be that way!'
    when :angry   then 'Woah, chill out!'
    when :curious then 'Sure.'
    else               'Whatever.'
    end
  end

end
