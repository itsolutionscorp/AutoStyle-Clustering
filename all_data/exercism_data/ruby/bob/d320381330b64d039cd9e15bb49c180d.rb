class Bob
  ANSWERS = { silence:  'Fine. Be that way!',
              shout:    'Woah, chill out!',
              question: 'Sure.' }
  ANSWERS.default =     'Whatever.'

  def hey(message)
    Responder.new(Phrase.new(message), ANSWERS).respond
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s.strip
  end

  def tone(messages)
    messages.each do |message|
      return message if self.send("is_#{message}?")
    end
    :default
  end

  private
    def is_silence?
      @phrase.empty?
    end
    def is_shout?
      @phrase == @phrase.upcase
    end
    def is_question?
      @phrase.end_with? '?'
    end
end

class Responder
  def initialize(phrase, answers)
    @phrase  = phrase
    @answers = answers
  end

  def respond
    tone = @phrase.tone(@answers.keys)
    @answers[tone]
  end
end
