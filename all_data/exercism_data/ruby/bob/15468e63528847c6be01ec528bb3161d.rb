class Bob
  def hey(str)
    if str.upcase == str && str =~ /[a-zA-Z]/
      'Woah, chill out!'
    elsif str =~ /\?\z/
      'Sure.'
    elsif str == '' || str =~ /\A\s*\z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
