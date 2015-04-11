module ConversationalTeen
  class Responder
    attr_reader :message

    def initialize(message)
      @message = message
    end
  end


  class LeetRequestResponder < Responder
    def match?
      message.start_with? 'Bro, '
    end

    def reply
      stripped_message.split('').map {|c| convert c}.join
    end

    def convert char
      charmap = {'a' => '4',
                 'e' => '3',
                 'l' => '1',
                 't' => '7'}

      char_result = charmap[char.downcase]
      char_result ? char_result : char
    end

    def stripped_message
      message[5..-1]
    end
  end


  class QuestionResponder < Responder
    def match?
      message.end_with? '?'
    end

    def reply
      'Sure.'
    end
  end


  class SilenceResponder < Responder
    def match?
      message.empty?
    end

    def reply
      'Fine. Be that way.'
    end
  end


  class StatementResponder < Responder
    def match?
      true
    end

    def reply
      'Whatever.'
    end
  end


  class YellingResponder < Responder
    def match?
      message.eql? message.upcase
    end

    def reply
      'Woah, chill out!'
    end
  end


  def self.reply_to(message)
    responders = SilenceResponder,
                 LeetRequestResponder,
                 QuestionResponder,
                 YellingResponder,
                 StatementResponder

    responders.map {|r| r.new message}.find(&:match?).reply
  end
end


class Bob
  def hey(message)
    ConversationalTeen.reply_to message.chomp
  end
end
