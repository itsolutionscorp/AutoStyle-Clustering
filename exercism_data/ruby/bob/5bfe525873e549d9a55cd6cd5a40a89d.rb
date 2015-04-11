class Sentence
  attr_reader :text
  def initialize(text)
    @text = text
  end

  def silence?
    text.strip.empty?
  end

  def all_caps?
    text == text.upcase
  end

  def question?
    text.end_with?("?")
  end
end

class Bob
  def hey(message)
    sentence = Sentence.new(message)
    if sentence.silence?
      "Fine. Be that way!"
    elsif sentence.all_caps?
      "Woah, chill out!"
    elsif sentence.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
