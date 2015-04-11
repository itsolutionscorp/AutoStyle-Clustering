class Bob
  ANSWERS = { silence:  'Fine. Be that way!',
              shout:    'Woah, chill out!',
              question: 'Sure.' }
  ANSWERS.default =     'Whatever.'

  def hey(message)
    phrase = Phrase.new(message)
    tone = phrase.tone(ANSWERS.keys)
    ANSWERS[tone]
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s.strip
  end

  def tone(messages)
    messages.find { |message| self.send("#{message}?") }
  end

  private
  def silence?
    @phrase.empty?
  end
  def shout?
    @phrase == @phrase.upcase
  end
  def question?
    @phrase.end_with? '?'
  end
end
