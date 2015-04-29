class Bob
  def hey(speech)
    if speech.strip.empty?
      'Fine. Be that way!'
    elsif speech.match(/\p{Lower}/).nil? and speech.scan(/[A-Z]/).any?
      'Woah, chill out!'
    elsif speech.end_with? "?"
      'Sure.'
    else
      'Whatever.'
    end
  end
end
