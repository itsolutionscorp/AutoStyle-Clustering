require 'delegate'

class Bob
  def hey(string)
    message = Message.new(string)

    case
    when message.none?
      'Fine. Be that way!'
    when message.shouted?
      'Whoa, chill out!'
    when message.question?
      'Sure.'
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

  def none?
    gsub(/\s+/, "").empty?
  end

  private

  def no_letters?
    self !~ /[a-zA-Z]/
  end
end
