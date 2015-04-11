# Represents a teenager one can talk to.
class Bob

  ANSWERS = {
   shout:    'Woah, chill out!',
   question: 'Sure.',
   silence:  'Fine. Be that way!',
   other:    'Whatever.'
  }

  def hey text
    message = Message.new(text)
    ANSWERS[message.kind]
  end

  # Represents a message Bob receives from correspondent.
  class Message < Struct.new(:text)
    def kind
      case
        when shout?    then :shout
        when question? then :question
        when silence?  then :silence
        else :other
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
      text.strip.empty?
    end

    def all_letters_caps?
      text == text.upcase
    end

    def has_letters?
      text.match /[a-z]/i
    end
  end
end
