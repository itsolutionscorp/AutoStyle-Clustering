class Bob
  def hey(speak)
    if speak.empty? || speak.match(/^\s*$/)
      'Fine. Be that way!'
    elsif speak.upcase == speak
      'Woah, chill out!'
    elsif speak[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
