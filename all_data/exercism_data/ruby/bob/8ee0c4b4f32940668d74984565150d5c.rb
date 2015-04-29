class Sentence
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def blank?
    phrase.strip.empty?
  end

  def shouting?
    phrase.match(/[A-Z]/) && phrase.upcase == phrase
  end

  def question?
    phrase[-1] == '?'
  end
end

class Bob
  def hey(remark)
    sentence = Sentence.new remark

    if sentence.blank?
      'Fine. Be that way!'
    elsif sentence.shouting?
      'Whoa, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
