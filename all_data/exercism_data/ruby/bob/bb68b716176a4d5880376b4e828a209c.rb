class Bob
  def hey text
    greeting = Greeting.new(text)
    respond_to greeting
  end

  private
  def respond_to greeting
    case true
      when greeting.silence? then "Fine. Be that way!"
      when greeting.yelling? then "Woah, chill out!"
      when greeting.question? then "Sure."
      else "Whatever."
    end
  end
end

class Greeting
  def initialize text
    @text = text
  end

  def silence?
    text.strip.empty?
  end

  def yelling?
    text.upcase == text
  end

  def question?
    text.end_with? "?"
  end

  private
  attr_reader :text
end
