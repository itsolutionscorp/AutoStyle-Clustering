class Bob

  def hey(arg)
    phrase = Phrase.new(arg.strip)

    if phrase.silence?
      "Fine. Be that way!"

    elsif phrase.yelling?
      "Woah, chill out!"

    elsif phrase.question?
      "Sure."

    else
      "Whatever."
    end
  end
end

class Phrase
  def initialize(words)
    @words = words
  end

  def silence?
    @words.empty?
  end

  def question?
    @words.end_with?('?')
  end

  def yelling?
    without_nums = @words.tr( '^a-zA-Z!', '')
    without_nums.upcase == without_nums and without_nums.length >= 2
  end
end
