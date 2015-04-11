class Bob

  def hey(given_sentence)
    @sentence = Sentence.new(given_sentence)

    case
    when shouting?
      "Woah, chill out!"
    when questioning?
      "Sure."
    when mute?
      "Fine. Be that way!"
    else
      "Whatever."
    end

  end

  private

  def shouting?
    @sentence.shout?
  end

  def questioning?
    @sentence.question?
  end

  def mute?
    @sentence.silent?
  end

end

class Sentence

  def initialize(sentence)
    @sentence = sentence.to_s.strip
  end

  def shout?
    !silent? && @sentence == @sentence.upcase
  end

  def question?
    @sentence.end_with? "?"
  end

  def silent?
    @sentence.empty?
  end

end
