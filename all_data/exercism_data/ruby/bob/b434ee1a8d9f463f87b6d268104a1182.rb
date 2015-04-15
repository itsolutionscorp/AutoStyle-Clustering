class Bob
  def hey(message)
    heard = WordsHeard.new(message)
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
  def initialize(words)
    @words = words.to_s
  end

  def question?
    @words.end_with?("?")
  end

  def yelling?
    @words.upcase == @words
  end

  def silence?
    @words.empty?
  end
end
