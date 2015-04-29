class Bob
  def hey(sentence)
    Conversation.new(sentence).respond
  end
end

class Conversation
  attr_reader :sentence

  # not sure if this belongs here,
  # since it's Bob who should know how to respond
  # and not the convo object
  RESPONSES = { :fine => "Fine. Be that way.",
                :sure => "Sure.",
                :woah => "Woah, chill out!",
                :whatever => "Whatever." }

  def initialize(sentence)
    @sentence = sentence
  end

  # personally I liked the multiline return statements more.
  def respond
    if self.is_meaningless?
      RESPONSES[:fine]
    elsif self.is_asking?
      RESPONSES[:sure]
    elsif self.is_shouting?
      RESPONSES[:woah]
    else
      RESPONSES[:whatever]
    end
  end

  protected

    def is_meaningless?
      sentence.nil? || sentence.empty?
    end

    def is_asking?
      sentence.end_with?("?")
    end

    def is_shouting?
      sentence == sentence.upcase
    end
end
