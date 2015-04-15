class Bob
  def hey(msg)
    msg.gsub!("\n", "")
    if is_silent?(msg)
      'Fine. Be that way!'
    elsif is_shouting?(msg)
      'Woah, chill out!'
    elsif is_asking?(msg)
      'Sure.'
    else
      "Whatever."
    end
  end

  def is_shouting?(msg)
    msg =~ /\p{Lu}/ && msg.upcase == msg
  end

  def is_asking?(msg)
    msg.end_with?('?')
  end

  def is_silent?(msg)
    msg.strip == ''
  end

  private :is_shouting?, :is_asking?, :is_silent?
end
