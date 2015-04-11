begin

  class Bob

    def hey(message)
      if not message or message.strip == ''  # silence
          return 'Fine. Be that way!'
      end
      if message.upcase == message  # shouting
          return 'Woah, chill out!'
      end
      if message[-1] == '?'  # question
          return 'Sure.'
      end
      'Whatever.'
    end
  end
end
