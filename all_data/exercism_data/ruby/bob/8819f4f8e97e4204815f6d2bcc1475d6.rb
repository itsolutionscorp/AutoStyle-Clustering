class Bob
  def expression(talk)
    if talk.nil? || talk.empty?
      'Fine. Be that way.'
    elsif talk.upcase.eql?(talk)
      'Woah, chill out!'
    elsif talk.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
