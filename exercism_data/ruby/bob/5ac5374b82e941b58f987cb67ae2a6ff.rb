class Bob
  def hey(message)
    message = Message.new(contents: message)

    respond_to message
  end

  def respond_to(message)
    if message.silence?
      "Fine. Be that way!"
    elsif message.shouting?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  FIELDS = [:contents]

  FIELDS.each do |field|
    attr_accessor field
  end

  def initialize(properties = {})
    properties.each do |property, value|
      if FIELDS.include? property.to_sym
        self.send "#{property}=", value
      end
    end
  end

  def shouting?
    @contents =~ /\A[^a-z]+\z/
  end

  def question?
    @contents =~ /\?\z/
  end

  def silence?
    @contents.strip.empty?
  end
end
