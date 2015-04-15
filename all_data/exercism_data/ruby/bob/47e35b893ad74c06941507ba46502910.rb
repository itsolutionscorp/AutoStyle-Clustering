class Bob
  def hey input
    if input.contains_at_least_one_letter? && input.doesnt_contain_lowercase_letters?
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
  def contains_at_least_one_letter?
    self.match(/[a-zA-Z]/)
  end
  def doesnt_contain_lowercase_letters?
    !self.match(/[a-z]/)
  end
end
