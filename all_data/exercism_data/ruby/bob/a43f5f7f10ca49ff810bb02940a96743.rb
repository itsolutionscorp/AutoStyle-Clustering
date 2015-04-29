class Bob

  def hey(input)

    sentence = Sentence.new(input.to_s.strip)

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

  def initialize(content)
    @content = content
  end

  def silence?
    @content.empty?
  end

  def question?
    @content.end_with? "?"
  end

  def yelling?
    @content.upcase == @content
  end

end
