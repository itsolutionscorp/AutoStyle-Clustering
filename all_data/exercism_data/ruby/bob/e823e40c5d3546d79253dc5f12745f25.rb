require 'delegate'
class Bob
  def hey(message)
    message_type = Message.new(message).type
    responses[message_type]
  end

  def responses
    {
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      silence: 'Fine. Be that way!',
      default: 'Whatever.'
    }
  end
end


class Message < SimpleDelegator
  def type
    if shouting?
      :shouting
    elsif question?
      :question
    elsif silent?
      :silence
    else
      :default
    end
  end

  def shouting?
    self.upcase == self && self.downcase != self
  end

  def question?
    self.end_with?('?')
  end

  def silent?
    self.strip.empty?
  end
end
