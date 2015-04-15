class Bob
  def hey(msg)
    if asking?(msg); 'Fine. Be that way!'
    elsif yelling?(msg); 'Woah, chill out!'
    elsif questioning?(msg); 'Sure.'
    else; 'Whatever.'
    end
  end

  private
  def asking?(msg); msg.to_s.empty? end
  def yelling?(msg); msg == msg.upcase end
  def questioning?(msg); msg.end_with? '?' end
end
