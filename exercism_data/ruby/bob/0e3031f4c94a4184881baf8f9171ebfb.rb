class Bob
  def hey(phrase)
    phrase = Phrase.new(phrase)
    case
      when phrase.silence? then 'Fine. Be that way!'
      when phrase.shouting? then 'Woah, chill out!'
      when phrase.question? then 'Sure.'
      else 'Whatever.'
    end
  end
end

class Phrase
  attr_reader :contents

  def initialize(phrase)
    @contents = phrase
  end

  def question?
    contents.end_with?('?')
  end

  def shouting?
    contents.upcase == contents
  end

  def silence?
    contents.to_s.strip.empty?
  end
end
