class Bob
  def hey(msg)
    if msg.nil? || msg.empty?
      'Fine. Be that way!'
    elsif msg == msg.each_char.map(&:capitalize).join('')
      'Woah, chill out!'
    elsif msg.end_with? '.', '!'
      'Whatever.'
    elsif msg.end_with? '?'
      'Sure.'
    end
  end
end
