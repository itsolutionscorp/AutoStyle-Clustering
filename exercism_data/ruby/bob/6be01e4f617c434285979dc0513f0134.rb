class Bob
  def hey words
    received_message = listen words
    return 'Woah, chill out!' if received_message.yelling?
    return 'Sure.' if received_message.asking?
    return 'Fine. Be that way!' if received_message.silence?
    'Whatever.'
  end

  def listen words
    ReceivedMessage.new(words)
  end

  ReceivedMessage = Struct.new(:words) do
    def asking?
      words.end_with? '?'
    end

    def yelling?
      words.match(/[A-Z]/) && words == words.upcase
    end

    def silence?
      words.strip == ''
    end
  end
end
