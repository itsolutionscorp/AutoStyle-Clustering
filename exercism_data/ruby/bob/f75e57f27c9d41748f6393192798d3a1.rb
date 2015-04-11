class Bob
  def hey(text)
    if text.nil? or text.empty?
      'Fine. Be that way.'
    elsif text !~ /[a-z]+/
      'Woah, chill out!'
    elsif text.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
