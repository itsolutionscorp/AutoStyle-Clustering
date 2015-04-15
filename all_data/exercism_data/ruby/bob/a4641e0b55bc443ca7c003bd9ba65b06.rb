class Bob
  def hey(sentence)
    reply_to Conversation.new(sentence)
  end

  private
    def reply_to(conversation)
      return "Fine. Be that way." if conversation.meaningless?
      return "Sure."              if conversation.asking?
      return "Woah, chill out!"   if conversation.shouting?
      return "Whatever."
    end
end

class Conversation
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def meaningless?
    sentence.nil? || sentence.empty?
  end

  def asking?
    sentence.end_with?("?")
  end

  def shouting?
    sentence == sentence.upcase
  end
end
