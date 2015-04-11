class Bob
  def hey(str)
    if str.strip.empty?
      'Fine. Be that way!'
    elsif str == str.upcase
      'Woah, chill out!'
    elsif str[str.length-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
