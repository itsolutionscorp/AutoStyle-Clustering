class Bob
  def hey(str)
    if str == nil || str == ''
      return "Fine. Be that way."
    elsif str[-1..-1] == '?'
      return 'Sure.'
    elsif str == str.upcase
      return 'Woah, chill out!'
    else
      return 'Whatever.'
    end
  end
end
