class Bob

  def hey(inquiry)
    if ignored? inquiry
      'Fine. Be that way!'
    elsif yelled? inquiry
      'Woah, chill out!'
    elsif questioned? inquiry
      'Sure.'
    else
      'Whatever.'
    end
  end

  def questioned? inquiry
    inquiry.end_with?("?")
  end

  def yelled? inquiry
    inquiry  == inquiry.upcase
  end

  def ignored? inquiry
    inquiry.nil? || inquiry.empty?
  end
end
