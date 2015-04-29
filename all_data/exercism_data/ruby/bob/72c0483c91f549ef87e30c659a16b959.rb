class Bob
  ANSWERS = {
    silence:  'Fine. Be that way!',
    yell:     'Woah, chill out!',
    question: 'Sure.',
    default:  'Whatever.'
  }

  def hey(speak)
    sentence = Sentence.new(speak)

    if sentence.silence?
      ANSWERS[:silence]
    elsif sentence.yell?
      ANSWERS[:yell]
    elsif sentence.question?
      ANSWERS[:question]
    else
      ANSWERS[:default]
    end
  end
end

class Sentence

  def initialize(content)
    @content = content
  end

  def yell?
    @content.upcase == @content
  end

  def question?
    @content.end_with? '?'
  end

  def silence?
    @content.strip.empty?
  end
end
