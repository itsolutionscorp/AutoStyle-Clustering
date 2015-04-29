class Bob

  def hey(arg)
    phrase = Phrase.new(arg.strip)

    if phrase.silence?
      return "Fine. Be that way!"

    elsif pharse.yelling?
      return "Woah, chill out!"

    elsif pharse.question?
      return "Sure."

    else
      return "Whatever."
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
    without_nums = @words.tr( '^a-zA-Z!', '').squeeze
    if without_nums.upcase == without_nums and without_nums.length >= 2
      return true
    else
      return false
    end
  end
end
end
