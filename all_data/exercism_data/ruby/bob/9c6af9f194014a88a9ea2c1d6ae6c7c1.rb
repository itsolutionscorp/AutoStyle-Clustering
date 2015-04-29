class Bob
  def hey address
    if nothing? address
      'Fine. Be that way!'
    elsif yelling? address
      'Woah, chill out!'
    elsif asking? address
      'Sure.'
    else 
      'Whatever.'
    end
  end

  private
  def nothing? address
    address.to_s.empty?
  end

  def yelling? address
    address.upcase == address
  end

  def asking? address
    address.end_with? '?'
  end
end
