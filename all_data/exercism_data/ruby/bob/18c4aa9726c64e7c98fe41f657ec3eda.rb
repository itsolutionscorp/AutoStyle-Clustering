class Bob

  def hey(words)
    respond_with_answer Speaker.new(words)
  end

  def respond_with_answer(speaker)
    case
    when speaker.shouting?
      "Woah, chill out!"
    when speaker.question?
      "Sure."
    when speaker.mute?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end

class Speaker

  attr_reader :words
  def initialize(words)
    @words = words
  end

  def shouting?
    words =~ /[A-Z]/ and words.upcase == words
  end

  def question?
    words.end_with? "?"
  end

  def mute?
    words.strip.empty?
  end

end
