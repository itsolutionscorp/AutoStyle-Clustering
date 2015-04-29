module Message
  def self.is_question(message)
    message =~ /\?\s*\z/
  end

  def self.is_yellin(message)
    message =~ /\A[A-Z\d\W]+\z/ && message =~ /[A-Z]/
  end

  def self.is_nuthin(message)
    message.strip == ''
  end
end

class Bob
  def hey(message)
    response = 'Whatever.'
    response = 'Sure.' if Message.is_question(message)
    response = 'Woah, chill out!' if Message.is_yellin(message)
    response = 'Fine. Be that way!' if Message.is_nuthin(message)
    response
  end
end
