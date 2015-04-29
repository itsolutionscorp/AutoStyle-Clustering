class Bob

  def hey(text)
    if text == text.upcase && /[A-Z]/ =~ text
      'Whoa, chill out!'
    elsif /[?]\z/ =~ text
      'Sure.'
    elsif text.strip.gsub(' ', '').length == 0
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
