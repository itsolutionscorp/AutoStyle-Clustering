class Bob
  def hey(message)
    message.extend Inflexion

    if message.shouting?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    elsif message.blank?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  module Inflexion
    def shouting?
      self == self.upcase && present?
    end

    def question?
      self[-1] == '?'
    end

    def blank?
      self !~ /[^[:space:]]/
    end

    def present?
      not blank?
    end
  end
end
