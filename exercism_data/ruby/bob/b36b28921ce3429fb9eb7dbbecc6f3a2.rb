class String
  def silence
    self.to_s == ''
  end

  def shout_or_forceful
    self == self.upcase
  end

  def question_or_prattle
    self.end_with?('?')
  end
end

class Bob
  def hey(message)
    if message.nil? or message.silence then 'Fine. Be that way!'
    elsif message.shout_or_forceful then 'Woah, chill out!'
    elsif message.question_or_prattle then 'Sure.'
    else 'Whatever.'
    end
  end
end
