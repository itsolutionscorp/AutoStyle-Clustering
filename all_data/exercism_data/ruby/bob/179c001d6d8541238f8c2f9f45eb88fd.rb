class Bob
  def initialize
    @responses = {:question => 'Sure.', :yelling => 'Woah, chill out!', :silence => 'Fine. Be that way!', :other => 'Whatever.'}
  end

  def hey(input)
    phrase_test = PhraseTester.new(input.strip)
    return @responses[:question] if phrase_test.question?
    return @responses[:yelling] if phrase_test.yelling?
    return @responses[:silence] if phrase_test.silence?
    return @responses[:other] if phrase_test.other?
  end

end

private

class PhraseTester
  def initialize(phrase)
    @phrase = phrase
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

  def other?
    !question? && !yelling? && !silence?
  end

end
