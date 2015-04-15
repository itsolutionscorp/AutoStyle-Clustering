class Bob
  def hey(message)
    message = Message.new(message)
    return 'Fine. Be that way!' if message.saying_nothing?
    return 'Woah, chill out!' if message.shouting?
    return 'Sure.' if message.asking?
    'Whatever.'
  end

  private
  class Message < String
    def saying_nothing?
      strip.empty?
    end

    def shouting?
      upcase == self && contains_alphabet_character?
    end

    def contains_alphabet_character?
      self =~ /\p{Alpha}/
    end

    def asking?
      end_with?('?')
    end
  end
end
