class Bob
  def hey(greeting)
    if greeting.match(/^\s*$/) && !greeting.include?("\n")
      'Fine. Be that way!'
    elsif (greeting.end_with?('?') && !(greeting.upcase == greeting)) || (greeting.end_with?('?') && !greeting.match(/[a-zA-Z]/))
      'Sure.'
    elsif greeting.upcase == greeting && greeting.match(/[a-zA-Z]/)
      'Woah, chill out!'
    else       
      'Whatever.'
    end
  end
end
