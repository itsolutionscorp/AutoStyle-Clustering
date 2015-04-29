class Bob

  def hey(input)

    sentence = Sentence.new(input)

    if sentence.silence?
      "Fine. Be that way!"
    elsif sentence.yelling?
      "Woah, chill out!"
    elsif sentence.question?
      "Sure."
    else
      "Whatever."
    end

  end

end

class Sentence

  attr_reader :content

  def initialize(content)
    @content = content
  end

  def silence?
    content.strip.empty? || (content == nil)
  end

  def question?
    content.strip.end_with? "?"
  end

  def yelling?
    content.upcase == content
  end

end
