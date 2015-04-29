class Bob

  # @param msg [String] The message Bob is responding to
  # @return response [String] Bob's response to the message
  def hey(msg)
    case message_type(msg)
    when :silent   then "Fine. Be that way!"
    when :shout    then "Woah, chill out!"
    when :question then "Sure."
    else                "Whatever."
    end
  end

  private

  def message_type(msg)
    MessageType.new(msg).message_type
  end

  # Determines the message_type for a given message
  # @param msg [String]
  MessageType = Struct.new(:msg) do

    # @return message_type [Symbol]
    def message_type
      case
      when silence?   then :silent
      when shouting?  then :shout
      when asking?    then :question
      when stating?   then :statement
      when demanding? then :demand
      else
        STDERR.puts "Could not determine message type for #{msg}"
      end
    end

    private

    def shouting?
      msg.upcase == msg
    end

    def stating?
      msg.end_with?('.')
    end

    def asking?
      msg.end_with?('?')
    end

    def demanding?
      msg.end_with?('!')
    end

    def silence?
      msg.nil? || msg.strip == ''
    end

  end

end
