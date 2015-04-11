class Bob
  def hey(input)
    sentence = Sentence.new(input)

    if sentence.shouting?
      "Woah, chill out!"
    elsif sentence.question?
      "Sure."
    elsif sentence.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Sentence
  def initialize(content_in)
    @content=content_in
  end

  def question?
    @content.end_with?("?")
  end

  def silence?
    @content.strip.empty?
  end

  def shouting?
    @content == @content.upcase and @content != @content.downcase
  end
end
