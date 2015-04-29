class Bob
  def hey phrase
    respond Speech.new(phrase)
  end

  private

  def respond speech
    if speech.silent?
      "Fine. Be that way."
    elsif speech.question?
      "Sure."
    elsif speech.yelling?
      "Woah, chill out!"
    elsif speech.statement?
      "Whatever."
    end
  end
end

class Speech
  def initialize(phrase)
    @phrase = String(phrase)
  end

  def question?
    phrase.end_with? '?'
  end

  def statement?
    not question?
  end

  def yelling?
    phrase == phrase.upcase
  end

  def silent?
    phrase.empty?
  end

  private

  attr_reader :phrase
end
