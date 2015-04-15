require 'delegate'

class Bob
  def hey(words)
    message = Message.new(words)

    if message.shouting? then chill_out
    elsif message.question? then sure
    elsif message.empty? then annoyed
    else whatever
    end
  end

  private

  def annoyed
    "Fine. Be that way!"
  end

  def whatever
    "Whatever."
  end

  def chill_out
    "Woah, chill out!"
  end

  def sure
    "Sure."
  end
end

class Message < SimpleDelegator
  def shouting?
    !match(/[a-z]/) && match(/[A-Z]/)
  end

  def question?
    end_with? '?'
  end

  def empty?
    strip.empty?
  end
end
