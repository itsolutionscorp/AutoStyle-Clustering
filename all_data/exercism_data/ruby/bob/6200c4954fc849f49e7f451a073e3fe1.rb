class Bob
  def hey(input)
    phrase = Phrase.new(input)
    return 'Sure.' if phrase.question?
    return 'Woah, chill out!' if phrase.yelling?
    return 'Fine. Be that way!' if phrase.silence?
    return 'Whatever.'
  end

end

private

class Phrase
  def initialize(phrase)
    @phrase = phrase.strip
  end

  def question?
    !yelling? && @phrase.end_with?('?')
  end

  def yelling?
    @phrase.upcase == @phrase && (@phrase.end_with?('!') || !silence?)
  end

  def silence?
    @phrase.empty?
  end

end

bob = Bob.new
puts bob.hey("Are you SHOUTING?")
