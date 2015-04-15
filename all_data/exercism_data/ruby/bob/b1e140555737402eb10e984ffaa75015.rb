class Bob
  def hey(input)
    phrase = Phrase.new(input)
    return 'Fine. Be that way!' if phrase.silent?
    return 'Woah, chill out!' if phrase.yelling?
    return 'Sure.' if phrase.question?
    'Whatever.'
  end
end

class Phrase
  attr_reader :phrase
  def initialize(input)
    @phrase = input
  end

  def yelling?
    phrase == phrase.upcase
  end

  def question?
    phrase.end_with???
  end
  
  def silent?
    phrase.strip.empty?
  end
end
