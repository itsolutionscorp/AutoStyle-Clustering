class Bob
  attr_reader :message

  def hey(statement)
    @message = Message.new(statement)
    respond_with_attitude
  end

  def respond_with_attitude
    case
    when message.is_silent?
      "Fine. Be that way!"
    when message.is_intense?
      "Woah, chill out!"
    when message.is_question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Message < Struct.new(:statement)
    def is_silent?
      statement.nil? || statement.empty? || statement.match(/^\s+$/)
    end

    def is_intense?
      statement.upcase == statement
    end

    def is_question?
      statement.match /\?$/
    end
  end
end
