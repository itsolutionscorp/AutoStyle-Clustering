class Bob
  def hey(input)
    phrase = Phrase.new(input)
    return 'Woah, chill out!' if phrase.yelling?
    return 'Sure.' if phrase.question?
    return 'Fine. Be that way!' if phrase.silence?
    return 'Whatever.'
  end

end

class Phrase
  def initialize(phrase)
    @phrase = phrase.strip
  end

  def question?
    @phrase.end_with?('?')
  end

  def yelling?
    words? && @phrase.upcase == @phrase
  end

  def silence?
    @phrase.empty?
  end

  private

  def words?
    @phrase.match(/[a-zA-Z]+/)
  end

end
