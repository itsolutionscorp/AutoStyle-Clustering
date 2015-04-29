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
    @phrase.length == 0
  end

  def shout?
    @phrase =~ /\A[^a-z]+\z/
  end

  def question?
    !shout? && @phrase =~ /\?\z/
  end
end
