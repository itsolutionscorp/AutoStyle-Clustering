class Bob
  def hey(text)
    if text.strip.length.zero?
      'Fine. Be that way!'
    elsif text =~ /[A-Z]/ && !(text =~ /[a-z]/)
      'Woah, chill out!'
    elsif text =~ /\?\Z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
