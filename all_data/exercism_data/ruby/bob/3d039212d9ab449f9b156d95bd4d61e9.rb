# Represents a teenager one can talk to.
class Bob

  ANSWERS_BY_MESSAGE_KIND = {
   shout: 'Woah, chill out!',
   question: 'Sure.',
   silence: 'Fine. Be that way!',
   other: 'Whatever.'
  }

  def hey text
    message = Message.new(text)
    ANSWERS_BY_MESSAGE_KIND[message.kind]
  end

  # Represents a message from correspondent.
  class Message < Struct.new(:text)
    def kind
      if shout?
        :shout
      elsif question?
        :question
      elsif silence?
        :silence
      else
        :other
      end
    end

    private

    def shout?
      has_letters? and all_letters_caps?
    end

    def question?
      text.end_with? '?'
    end

    def silence?
      text.gsub(/\s/, '').empty?
    end

    def all_letters_caps?
      text == text.upcase
    end

    def has_letters?
      text.match /[a-z]/i
    end
  end
end
