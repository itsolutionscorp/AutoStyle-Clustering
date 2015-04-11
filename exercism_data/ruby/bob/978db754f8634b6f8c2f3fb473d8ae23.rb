class Bob
  def hey(string)
    case
    when string.is_blank?      then 'Fine. Be that way!'
    when string.is_upcase?     then 'Woah, chill out!'
    when string.is_a_question? then 'Sure.'
    else                            'Whatever.'
    end
  end
end

class NilClass
  def is_blank?
    true
  end
end

class String
  def is_a_question?
    self.last_character.is_a_question_mark?
  end

  def is_blank?
    if self.length > 0
      self.each_char do |character|
        return false if character != " "
      end
    end
    true
  end

  def is_a_question_mark?
    self == "?"
  end

  def last_character
    self.length == 0 ? "" : self[-1]
  end

  def is_upcase?
    self.upcase == self
  end
end
