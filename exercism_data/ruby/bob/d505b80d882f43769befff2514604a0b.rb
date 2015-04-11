class Bob
  def hey input
    if input.contains_a_letter? && !input.contains_a_lowercase_letter?
      'Woah, chill out!'
    elsif input.end_with? '?'
      'Sure.'
    elsif input.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class String
  def contains_a_letter?
    self.match(/[a-zA-Z]/)
  end
  def contains_a_lowercase_letter?
    self.match(/[a-z]/)
  end
end
