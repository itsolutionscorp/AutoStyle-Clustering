class Bob
  def hey(chain)
    if chain.nil? || chain =~ /^$/
      'Fine. Be that way!'
    elsif not chain =~ /[a-z]/
      'Woah, chill out!'
    elsif chain =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
