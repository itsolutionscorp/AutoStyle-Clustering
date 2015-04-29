class Bob
  def hey address
    if address.nil? or address.empty?
      'Fine. Be that way!'
    elsif address.upcase == address
      'Woah, chill out!'
    elsif address.end_with? '?'
      'Sure.'
    else 
      'Whatever.'
    end
  end
end
