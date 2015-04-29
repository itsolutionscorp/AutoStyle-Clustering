class Bob
  def hey (param1)
    if param1.nil? || param1.strip.length < 1
      'Fine. Be that way!'
    elsif param1.match(/\p{Lower}/).to_s.length < 1
      'Woah, chill out!'
    elsif param1[-1,1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
