# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class Bob
  def hey(phrase)
    responses.fetch(Phrase.new(phrase).type, 'Whatever.')
  end

  private

  def responses
    { shouting: 'Woah, chill out!', question: 'Sure.', silence: 'Fine. Be that way!' }
  end
end

class Phrase
  attr_reader :phrase
  def initialize(string)
    @phrase = string
  end

  def type
    silence? || shouting? || question?
  end

  private

  def silence?
    phrase.strip.empty? && :silence
  end

  def shouting?
    phrase == phrase.upcase && :shouting
  end

  def question?
    phrase.end_with?('?') && :question
  end
end
