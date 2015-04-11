class Bob
  def hey(content)
    msg = Message.new content
    return 'Fine. Be that way!' if msg.asking?
    return 'Woah, chill out!'   if msg.yelling?
    return 'Sure.'              if msg.questioning?
    'Whatever.'
  end
end

class Message < Struct.new :content
  def asking?
    content.to_s.empty?
  end

  def yelling?
    content == content.upcase
  end

  def questioning?
    content.end_with? '?'
  end
end
