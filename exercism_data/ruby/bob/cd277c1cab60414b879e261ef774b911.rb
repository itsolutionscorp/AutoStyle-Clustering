begin

  class Bob

    def hey message
      if not message or message.strip == ''
          return 'Fine. Be that way!'
      end
      if message.upcase == message
          return 'Woah, chill out!'
      end
      if message[-1] == '?'
          return 'Sure.'
      end
      'Whatever.'
    end
  end
end
