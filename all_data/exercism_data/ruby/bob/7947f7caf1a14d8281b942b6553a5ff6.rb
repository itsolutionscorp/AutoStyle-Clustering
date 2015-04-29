class Bob

  def hey(s)
    if /[A-Z]/ =~ s and s.upcase == s
      'Woah, chill out!'
    elsif /\? *\z/ =~ s
      'Sure.'
    elsif /\A *\z$/ =~ s
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
