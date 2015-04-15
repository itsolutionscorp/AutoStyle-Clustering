class Bob
  def hey(text)
    if text.upcase == text && text.downcase != text
      'Whoa, chill out!'
    elsif text[-1] == '?'
      'Sure.'
    elsif text.strip == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
