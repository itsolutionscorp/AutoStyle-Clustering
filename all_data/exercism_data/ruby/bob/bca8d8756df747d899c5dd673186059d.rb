class Bob
  ANSWERS = {
    silence:  'Fine. Be that way!',
    yell:     'Woah, chill out!',
    question: 'Sure.',
    default:  'Whatever.'
  }

  def hey(speak)
    sentence = Sentence.new(speak)

    case
    when sentence.silence?  then ANSWERS[:silence]
    when sentence.yell?     then ANSWERS[:yell]
    when sentence.question? then ANSWERS[:question]
    else ANSWERS[:default]
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
