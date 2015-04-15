class Bob
  def hey(msg)
    if /\?$/.match(msg)
      'Sure.'
    elsif /^[A-Z]+$/.match(msg)
      'Woah, chill out!'
    elsif msg.nil?
      'Fine, Be that way!'
    else
      'Whatever.'
    end
  end
end
