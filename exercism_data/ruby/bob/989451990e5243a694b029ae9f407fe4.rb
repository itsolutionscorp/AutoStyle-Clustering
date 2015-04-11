module ConversationalTeen
  class LeetRequestResponder
    def match?(message)
      @message = message[/\ABro, (.*)/, 1]
      @message
    end

    def reply
      charmap = {'a' => '4',
                 'e' => '3',
                 'l' => '1',
                 't' => '7'}

      converted_array = @message.split('').map do |c|
        downc = c.downcase
        charmap[downc] ? charmap[downc] : c
      end

      converted_array.join
    end
  end


  class QuestionResponder
    def match?(message)
      message.end_with? '?'
    end

    def reply
      'Sure.'
    end
  end


  class SilenceResponder
    def match?(message)
      message.empty?
    end

    def reply
      'Fine. Be that way.'
    end
  end


  class StatementResponder
    def match?(message)
      true
    end

    def reply
      'Whatever.'
    end
  end


  class YellingResponder
    def match?(message)
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

    responders.map(&:new)
      .find {|r| r.match? message}
      .reply
  end
end


class Bob
  def hey(message)
    ConversationalTeen.reply_to message.chomp
  end
end
