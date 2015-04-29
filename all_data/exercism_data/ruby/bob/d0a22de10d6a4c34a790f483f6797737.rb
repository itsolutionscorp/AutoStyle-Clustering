class Bob

  def hey(text)
    if !text.match(/\S/)
      'Fine. Be that way!'
    elsif text == text.upcase
      "Woah, chill out!"
    elsif text.split('').last == '?'
      'Sure.'
    else
      "Whatever."
    end
  end
end
