class Bob
  def hey(str)
    if !str.is_a?(String)
      return 'Whatever.'
    end
    str.strip!
    if str.empty?
      'Fine. Be that way!'
    elsif str.upcase == str
      'Woah, chill out!'
    elsif str[-1,1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
