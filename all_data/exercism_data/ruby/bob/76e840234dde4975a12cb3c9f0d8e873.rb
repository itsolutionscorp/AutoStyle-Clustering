class Bob
  def hey(message)
    case
    when message.doesnt_say_anything?
      'Fine. Be that way!'
    when message.is_a_question?
      'Sure.'
    when message.is_all_caps?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class String
  def is_a_question?
    return false if self.is_all_caps?
    self[-1] == '?'
  end

  def is_all_caps?
    return false if self.is_only_numbers?
    self == self.upcase
  end

  def is_only_numbers?
    (self =~ /^[^a-zA-Z]+$/) != nil
  end

  def doesnt_say_anything?
    (self =~ /\A *\z/) != nil
  end
end
