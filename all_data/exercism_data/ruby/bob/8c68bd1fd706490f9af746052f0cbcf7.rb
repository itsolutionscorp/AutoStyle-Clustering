class Bob
  def hey(string)
    if string.nil? || string.empty?
      'Fine. Be that way.'
    elsif string.upcase == string
      'Woah, chill out!'
    elsif string[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
