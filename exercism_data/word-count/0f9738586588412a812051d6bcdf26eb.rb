class Bob
  def hey(statement)
    message = Message.new(statement)
    case
    when message.silent?
      "Fine. Be that way!"
    when message.intense?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end

  Message = Struct.new(:statement) do
    def silent?
      statement.nil? || statement.match(/^\s*$/)
    end

    def intense?
      statement.upcase == statement
    end

    def question?
      statement.match /\?$/
    end
  end
end
