class Bob
  def hey(msg)
    if msg == msg.upcase && msg.match(/[A-Z]/)
      'Woah, chill out!'
    elsif msg.end_with?('?')
      'Sure.'
    elsif msg.match(/\A[[:space:]]*\z/)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
