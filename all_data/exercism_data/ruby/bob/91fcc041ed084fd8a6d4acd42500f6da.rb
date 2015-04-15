class Bob
  ANSWERS = {
      question: 'Sure.',
      yelling: 'Woah, chill out!',
      silence: 'Fine. Be that way!',
      anything: 'Whatever.'
  }

  def hey(text)
    sentence = Sentence.new(text)
    answer_for(sentence.kind)
  end

  def answer_for(kind)
    ANSWERS[kind]
  end
end

class Sentence
  def initialize(text)
    @text = text
  end

  def kind
    case
    when silence? then :silence
    when yelling? then :yelling
    when question? then :question
    else :anything
    end
  end

  private
  def question?
    @text.end_with?('?') && !yelling?
  end

  def yelling?
    @text == @text.upcase && !silence?
  end

  def silence?
    @text.strip.empty?
  end
end
