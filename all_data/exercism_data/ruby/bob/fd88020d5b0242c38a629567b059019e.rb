class Bob
  def hey(msg)
    heard = WordsHeard.new(msg)
    if heard.silence?
      "Fine. Be that way!"
    elsif heard.yelling?
      "Woah, chill out!"
    elsif heard.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class WordsHeard
  def initialize(msg)
    @msg = msg.to_s
  end

  def question?
    @msg.end_with?("?")
  end

  def yelling?
    @msg.upcase == @msg
  end

  def silence?
    @msg.empty?
  end
end
