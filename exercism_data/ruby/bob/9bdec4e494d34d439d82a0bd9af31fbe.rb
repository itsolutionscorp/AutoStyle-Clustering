class Bob
  def hey(sentence)
    reply_to Conversation.new(sentence)
  end

  private
    def reply_to(conversation)
      case
      when conversation.meaningless? then "Fine. Be that way."
      when conversation.asking?      then "Sure."
      when conversation.shouting?    then "Woah, chill out!"
      else
        "Whatever."
      end
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
