class Bob
  RESPONSES =
      {
        :question => 'Sure.',
        :statement => 'Whatever.',
        :yelling => 'Woah, chill out!',
        :silence => 'Fine. Be that way!'
      }

  def hey(say)
    sentence = Sentence.new(say)
    case
    when sentence.silence?
      RESPONSES[:silence]
    when sentence.yelling?
      RESPONSES[:yelling]
    when sentence.question?
      RESPONSES[:question]
    else
      RESPONSES[:statement]
    end
  end
end

class Sentence
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def silence?
    sentence.empty?
  end

  def yelling?
    sentence.upcase == sentence
  end

  def question?
    sentence.end_with?('?')
  end
end
