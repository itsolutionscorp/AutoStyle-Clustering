class Bob
  REPLIES = {
    chill_out: 'Woah, chill out!',
    sure: 'Sure.',
    fine: 'Fine. Be that way!',
    whatever: 'Whatever.'
  }

  def hey(message)
    message.extend Inflexion

    if message.shouting?
      REPLIES[:chill_out]
    elsif message.question?
      REPLIES[:sure]
    elsif message.blank?
      REPLIES[:fine]
    else
      REPLIES[:whatever]
    end
  end

  module Inflexion
    def shouting?
      self == self.upcase && present?
    end

    def question?
      end_with?('?')
    end

    def blank?
      self !~ /[^[:space:]]/
    end

    def present?
      not blank?
    end
  end
end
