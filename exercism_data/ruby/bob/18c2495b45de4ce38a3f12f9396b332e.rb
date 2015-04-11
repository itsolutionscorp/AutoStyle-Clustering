class Bob
  def hey(words)
    speech = Speech.new(words)
    case
    when speech.silence?  then 'Fine. Be that way.'
    when speech.caps?     then 'Woah, chill out!'
    when speech.question? then 'Sure.'
    else                       'Whatever.'
    end
  end
end

class Speech
  def initialize(sentence)
    @sentence = sentence
  end

  def silence?
    @sentence.to_s.empty?
  end

  def caps?
    @sentence == @sentence.upcase
  end

  def question?
    @sentence.end_with? "?"
  end
end
