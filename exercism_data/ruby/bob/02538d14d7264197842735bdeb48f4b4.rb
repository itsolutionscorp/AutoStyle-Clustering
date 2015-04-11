class Bob
  def hey(greeting)
    if greeting.match(/^\s*$/) && !greeting.include?("\n")
      'Fine. Be that way!'
    elsif greeting.upcase == greeting && greeting.match(/[a-zA-Z]/)
      'Woah, chill out!'
    elsif greeting.end_with?('?') && (!(greeting.upcase == greeting) || !greeting.match(/[a-zA-Z]/))
      'Sure.'
    else       
      'Whatever.'
    end
  end
end
