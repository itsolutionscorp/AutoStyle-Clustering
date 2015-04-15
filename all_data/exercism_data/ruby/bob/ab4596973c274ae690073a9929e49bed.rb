class Bob
  def hey(msg)
    if msg.nil? || msg.strip == ''
      'Fine. Be that way!'
    elsif msg == msg.upcase
      "Woah, chill out!"
    elsif msg.slice(-1) == '?'
      "Sure."
    else
      "Whatever."
    end
  end
end
