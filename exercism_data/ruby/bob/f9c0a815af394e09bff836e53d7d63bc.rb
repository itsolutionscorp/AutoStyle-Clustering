class Bob
  def hey phrase
    phrase = Phrase.new phrase

    return 'Fine. Be that way!' if phrase.silence?
    return 'Woah, chill out!' if phrase.shout?
    return 'Sure.' if phrase.question?

    'Whatever.'
  end
end

class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end

  def silence?
    @phrase.empty?
  end

  def shout?
    @phrase == @phrase.upcase
  end

  def question?
    return false if shout?
    @phrase.end_with?('?')
  end
end
