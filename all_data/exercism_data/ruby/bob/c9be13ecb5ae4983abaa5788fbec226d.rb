class Bob
  def hey(sentence)
    @conversation = Conversation.new(sentence)
    respond
  end

  private

    def respond
      return "Fine. Be that way." if @conversation.is_meaningless?
      return "Sure."              if @conversation.is_asking?
      return "Woah, chill out!"   if @conversation.is_shouting?
      return "Whatever."
    end
end

class Conversation
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

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
