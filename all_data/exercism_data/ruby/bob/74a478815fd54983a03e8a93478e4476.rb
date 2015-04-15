class Bob
  def hey(input)
    phrase_test = PhraseTester.new(input)
    return 'Sure.' if phrase_test.question?
    return 'Woah, chill out!' if phrase_test.yelling?
    return 'Fine. Be that way!' if phrase_test.silence?
    return 'Whatever.'
  end

end

private

class PhraseTester
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
