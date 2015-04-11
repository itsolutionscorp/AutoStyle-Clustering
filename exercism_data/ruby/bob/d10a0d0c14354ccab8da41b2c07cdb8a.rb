class Bob
  def hey(str)
    if str =~ /[a-w]/i && str.upcase == str
      'Woah, chill out!'
    elsif str.end_with?('?')
      'Sure.'
    elsif str.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
