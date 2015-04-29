class Bob
  def hey(text)
    if text.to_s.strip.empty?
      'Fine. Be that way!'
    elsif text.upcase.eql?(text)
      'Woah, chill out!'
    elsif text.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
