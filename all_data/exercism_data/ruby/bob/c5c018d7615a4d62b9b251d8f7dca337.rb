class Bob
  def hey message
    self.send TeenagerBehavior.react_to(message)
  end

  def dismiss
    "Sure."
  end

  def cool
    "Woah, chill out!"
  end

  def default
    "Whatever."
  end

  def annoyed
    'Fine. Be that way!'
  end
end

class TeenagerBehavior
  MessageClassifiers = %i( silent? yelling? question? )

  class << self
    def react_to message
      MessageClassifiers.each do |method|
        if reaction = self.send(method, message)
          return reaction
        end
      end
      return :default
    end

    def yelling? message
      :cool if message[/[a-zA-Z]/] && message == message.upcase
    end

    def question? message
      :dismiss if message[-1] == '?'
    end

    def silent? message
      :annoyed if message.gsub(/\s/, '') == ''
    end
  end
end
