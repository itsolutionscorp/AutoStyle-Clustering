class Bob
  def hey(string)
    if string !~ /\w/
      'Fine. Be that way!'
    elsif string =~ /[A-Z]/ && string !~ /[a-z]/
      'Whoa, chill out!'
    elsif string =~ /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
