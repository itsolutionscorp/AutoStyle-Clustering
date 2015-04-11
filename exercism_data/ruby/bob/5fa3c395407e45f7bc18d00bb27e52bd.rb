class Bob
  def hey(sentence_content)
    sentence = Sentence.new(sentence_content)

    case
      when sentence.silence?
        'Fine. Be that way!'
      when sentence.yelling?
        'Woah, chill out!'
      when sentence.question?
        'Sure.'
      else
        'Whatever.'
    end
  end
end

class Sentence
  attr_reader :content

  def initialize(content)
    @content = content
  end

  def yelling?
    content.upcase == content
  end

  def question?
    content.end_with?('?')
  end

  def silence?
    content.strip.empty?
  end
end
