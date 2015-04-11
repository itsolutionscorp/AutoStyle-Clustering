class Bob
  def hey(str)
    if str.upcase == str && (str =~ /[A-Za-z]/)
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
