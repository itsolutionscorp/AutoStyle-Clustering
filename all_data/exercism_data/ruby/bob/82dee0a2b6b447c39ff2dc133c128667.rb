class Bob
  def hey message
    if empty? message
      'Fine. Be that way!'
    elsif screaming? message 
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  private 

  def empty? message
    message.strip.empty?
  end

  def screaming? message
    not message.each_char.detect { |c| c =~ /[[:lower]]/ }
  end

  def question? message
    message.each_char.to_a.last == '?'
  end
end
