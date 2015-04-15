class Bob
  def hey(message_to_bob)
    if message_to_bob.is_empty_or_only_spaces?
      'Fine. Be that way!'
    elsif message_to_bob.has_all_letters_capitalized?
      'Woah, chill out!'
    elsif message_to_bob.ends_with('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class String
  def is_empty_or_only_spaces?
    self.strip.empty?
  end

  def has_all_letters_capitalized?
    self.match(/[a-zA-Z]+/) && self.upcase == self
  end

  def ends_with(character)
    self[-1] == character
  end
end
