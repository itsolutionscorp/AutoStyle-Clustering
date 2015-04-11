class Bob
  def hey(msg='')
    msg = Message.new(msg.strip)

    if msg.silence?
      'Fine. Be that way!'
    elsif msg.yelling?
      'Woah, chill out!'
    elsif msg.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

Message = Struct.new(:content) do
  def yelling?
    content.upcase == content
  end

  def question?
    content[-1] == '?'
  end

  def silence?
    content.strip == ''
  end
end
