class Bob
  def hey(msg)
    case
    when msg.empty? || msg.strip.empty?
      'Fine. Be that way!'
    when msg.upcase == msg && !(/[a-zA-z]/=~msg).nil?
      'Woah, chill out!'
    when msg.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
end
