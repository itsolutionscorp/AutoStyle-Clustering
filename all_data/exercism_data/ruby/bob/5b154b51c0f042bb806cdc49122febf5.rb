require 'byebug'

class Bob

  def hey(phrase)

    reply = 'Whatever.'
    if phrase.gsub(/\s+/, "").length == 0
      reply = 'Fine. Be that way!'
    elsif phrase.is_yelling?
      reply = 'Whoa, chill out!'
    elsif phrase.is_question?
      reply = 'Sure.'
    end

    reply
  end
end

class String
  def is_yelling?
    self.gsub(/[\s+\d\W]/, "") == self.gsub(/[^A-Z]/,"") && self.has_letters?
  end
  def is_question?
    self.end_with?("?")
  end
  def has_letters?
    !self.gsub(/[^a-z]/i, '').empty?
  end
end
