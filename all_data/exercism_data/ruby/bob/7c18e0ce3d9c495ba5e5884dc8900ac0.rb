require 'delegate'

class Bob
  def hey(string)
    message = Message.new(string)

    case
    when message.empty?
      'Fine. Be that way!'
    when message.question? && !message.shouted?
      'Sure.'
    when message.shouted?
      'Whoa, chill out!'
    else
      'Whatever.'
    end
  end
end

class Message < SimpleDelegator
  def question?
    end_with? '?'
  end

  def shouted?
    return false if no_letters?
    self == upcase
  end

  def empty?
    gsub(/\s+/, "").empty?
  end

  private

  def no_letters?
    self !~ /[a-zA-Z]/
  end
end
