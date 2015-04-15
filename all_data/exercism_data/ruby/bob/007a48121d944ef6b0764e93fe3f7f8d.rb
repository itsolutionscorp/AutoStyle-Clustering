class Bob

  def hey salute
    if salute.gsub(/[^a-zA-Z]/, '') =~ /\A[A-Z]+\Z/ and salute =~ /^[A-Z0-9,\s[[:punct:]]]+\Z/
      'Woah, chill out!'
    elsif salute =~ /\?\Z/
      'Sure.'
    elsif salute.empty? or salute =~ /\A\s+\Z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
