class Bob
  def hey (my_string)
    if my_string.is_all_uppercase?
      # He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
      'Woah, chill out!'
    elsif my_string.end_with? '?'
      # Bob answers 'Sure.' if you ask him a question.
      'Sure.'
    elsif my_string.strip.empty?
      # He says 'Fine. Be that way!' if you address him without actually saying anything.
      'Fine. Be that way!'
    else
      # He answers 'Whatever.' to anything else.
      'Whatever.'
    end
  end
end

class String
  def is_all_uppercase?
    if !self.match(/[a-z]/) && self.match(/[A-Z]/)
      true
    else
      false
    end
  end
end
