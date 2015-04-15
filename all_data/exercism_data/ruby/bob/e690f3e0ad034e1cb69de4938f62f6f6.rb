class Bob
  def hey message
    message_intent = InterpretedMessage.new message

    case
      when message_intent.yelling_at_me?     then "Woah, chill out!"
      when message_intent.asking_a_question? then "Sure."
      when message_intent.saying_nothing?    then "Fine. Be that way!"
      else
        "Whatever."
      end
  end

  class InterpretedMessage < Struct.new :message
    def asking_a_question?
      ends_with_question_mark?
    end

    def saying_nothing?
      completely_blank?
    end

    def yelling_at_me?
      all_caps?
    end

    private

    def completely_blank?
      message.strip.empty?
    end

    def ends_with_question_mark?
      message.match /\?\z/
    end

    def all_caps?
      message.gsub(/[^a-zA-Z]/, "").match /\A[A-Z|\s]+\z/
    end
  end
end
