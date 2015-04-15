require 'pry'
class Bob
  def hey string
    string.strip!
    if string.upcase == string && string =~ /[a-zA-Z]/
      'Woah, chill out!'
    elsif string =~ /\?\Z/
      'Sure.'
    elsif string == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
