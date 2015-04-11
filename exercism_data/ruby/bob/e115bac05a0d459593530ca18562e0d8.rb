class Bob
  def hey(msg_string)
    msg = Message.new(msg_string)

    return 'Fine. Be that way!' if msg.silence?
    return 'Woah, chill out!' if msg.shouted?
    return 'Sure.' if msg.asked?
    'Whatever.'
  end
end

class Message
  attr_reader :str

  def initialize(str)
    @str = str
  end

  def silence?
    not str or str.empty?
  end

  def shouted?
    str !~ /[a-z]/
  end

  def asked?
    str.end_with?('?')
  end
end
