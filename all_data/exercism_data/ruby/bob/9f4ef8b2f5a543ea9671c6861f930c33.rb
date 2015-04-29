class Bob
  def hey(words)
    phrase = Phrase.new(words)
    if phrase.shout?
      return 'Woah, chill out!'
    elsif phrase.question?
      return 'Sure.'
    elsif phrase.silence?
      return 'Fine. Be that way!'
    end
    'Whatever.'
  end
end

class Phrase

  def initialize(words)
    @words = words
  end

  def shout?
    lowercase.empty? and !caps.empty?
  end

  def question?
    @words.end_with? '?'
  end

  def silence?
    @words.strip.empty?
  end

  private

  def lowercase
    @words.tr('^a-z', '')
  end

  def caps
    @words.tr('^A-Z', '')
  end

end
