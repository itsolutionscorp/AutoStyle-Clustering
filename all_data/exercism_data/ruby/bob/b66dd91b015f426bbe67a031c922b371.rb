class Phrase
  def initialize str
    @str = str.to_s
  end

  def silence? 
    @str.strip.empty?
  end

  def shout? 
    @str.upcase == @str
  end

  def question? 
	  @str.end_with? '?'
  end
end


class Bob
  def hey str
    what_was_said = Phrase.new(str)

    return 'Fine. Be that way!' if what_was_said.silence?
    return 'Woah, chill out!' if what_was_said.shout?
    return 'Sure.' if what_was_said.question?
    return 'Whatever.'
  end
end
