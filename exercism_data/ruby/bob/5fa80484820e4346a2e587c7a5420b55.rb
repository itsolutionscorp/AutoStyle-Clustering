class Bob
  def hey(incoming)
    if incoming.nil? || '' == incoming.strip
      'Fine. Be that way!'
    elsif incoming == incoming.upcase
      'Woah, chill out!'
    elsif incoming.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
