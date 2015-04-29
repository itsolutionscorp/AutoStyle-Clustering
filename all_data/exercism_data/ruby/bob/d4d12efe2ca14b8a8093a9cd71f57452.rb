class Bob
  def hey(msg)
    if msg.strip.empty?
      'Fine. Be that way!'
    elsif msg =~ /^[0-9,a-z\s]+$/
      'Whatever.'
    elsif msg =~ /[A-Z]/ && msg =~ /^[0-9,A-Z!\s?%^*@\#$(*^]+$/
      'Whoa, chill out!'
    elsif msg =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
