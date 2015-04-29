class Bob
  def hey(msg)
    msg = Message.new(msg || '')

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
    stripped.end_with?(??)
  end

  def silence?
    stripped == ''
  end

  private

  def stripped
    content.strip
  end
end
