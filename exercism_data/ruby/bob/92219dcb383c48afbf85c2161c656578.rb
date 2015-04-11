class Bob
  attr_reader :responses

  def initialize
    @responses =
      {
        :question => 'Sure.',
        :statement => 'Whatever.',
        :yelling => 'Woah, chill out!',
        :silence => 'Fine. Be that way!'
      }
  end

  def hey(say)
    sentence = Sentence.new(say)
    case
    when sentence.silence?
      responses[:silence]
    when sentence.yelling?
      responses[:yelling]
    when sentence.question?
      responses[:question]
    else
      responses[:statement]
    end
  end
end

class Sentence
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def silence?
    sentence.to_s.empty?
  end

  def yelling?
    sentence.upcase == sentence
  end

  def question?
    sentence.end_with?('?')
  end
end
