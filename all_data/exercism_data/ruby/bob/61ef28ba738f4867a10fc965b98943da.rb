class Bob
  RESPONSES = {
    :dismissal    => "Fine. Be that way.",
    :serenity     => "Woah, chill out!",
    :acceptance   => "Sure.",
    :indifference => "Whatever.",
  }

  # @param msg [String] The message Bob is responding to
  # @return response [String] Bob's response to the message
  def hey(msg)
    message_type = MessageType.new(msg).message_type
    response_type = ResponseType.new(message_type).response_type
    RESPONSES.fetch(response_type)
  end

  # Determines the response_type for a given message
  # @param msg [String]
  ResponseType = Struct.new(:message_type) do
    TYPES = {
      silent:    :dismissal,
      shout:     :serenity,
      question:  :acceptance,
      statement: :indifference,
      demand:    :indifference,
    }

    # @return response_type [Symbol]
    def response_type
      TYPES.fetch(message_type)
    end

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
        STDERR.puts "Could not determine response for #{msg}"
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
      msg.nil? || msg == ''
    end

  end

end
