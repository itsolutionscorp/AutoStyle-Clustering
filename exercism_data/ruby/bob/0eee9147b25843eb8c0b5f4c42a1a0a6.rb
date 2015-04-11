class Bob
  def hey(text)
    text = text.to_s
    text.strip!
    if text.empty?
      'Fine. Be that way!'
    elsif text == text.upcase
      'Woah, chill out!'
    elsif text.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
end
