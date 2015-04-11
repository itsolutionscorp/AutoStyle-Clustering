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
    RESPONSES[sentence.type]
  end
end

class Sentence
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def type
    case
    when silence?
      :silence
    when yelling?
      :yelling
    when question?
      :question
    else
      :statement
    end
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
