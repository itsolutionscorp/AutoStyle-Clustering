class Bob
  def hey(anything)
    if anything.upcase == anything && anything =~ /[a-zA-Z]/
      'Woah, chill out!'
    elsif anything =~ (/\?\z/)
      'Sure.'
    elsif anything =~ (/^\s*\z/)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
