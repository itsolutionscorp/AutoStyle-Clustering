class Bob
  def hey(ask)
    ask = ask.strip

    if ask.empty?
      'Fine. Be that way!'
    elsif ask.upcase == ask
      'Woah, chill out!'
    elsif ask.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
