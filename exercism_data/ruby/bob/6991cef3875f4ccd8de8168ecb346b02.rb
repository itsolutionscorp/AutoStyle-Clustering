class Sentence
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def nothing?
    phrase.strip.empty?
  end

  def shouting?
    phrase.match(/[A-Z]/) && phrase.upcase == phrase
  end

  def question?
    phrase[-1] == '?'
  end

  def default?
    !nothing? && !shouting? && !question?
  end
end

class Bob
  ANSWERS = {
    nothing: 'Fine. Be that way!',
    shouting: 'Whoa, chill out!',
    question: 'Sure.',
    default: 'Whatever.'
  }

  def hey(remark)
    sentence = Sentence.new remark

    ANSWERS.each do |remark, response|
      return response if sentence.send("#{remark}?")
    end
  end
end
