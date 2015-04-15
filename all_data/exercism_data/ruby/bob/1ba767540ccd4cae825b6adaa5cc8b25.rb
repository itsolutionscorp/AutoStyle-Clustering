class Bob
  def hey(text)
    sentence = Sentence.new(text)
    case
    when sentence.silence?  then "Fine. Be that way!"
    when sentence.yell?     then "Woah, chill out!"
    when sentence.question? then "Sure."
    else                         "Whatever."
    end
  end
end

class Sentence
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def silence?
    text.strip.empty?
  end

  def yell?
    text =~ /[a-z]/i and text == text.upcase
  end

  def question?
    text.end_with? "?"
  end
end
