class Bob
  def hey(message)
    case
    when silent?(message) then 'Fine. Be that way!'
    when shouting?(message) then 'Woah, chill out!'
    when asking_question?(message) then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def silent?(message)
    !message || message =~ /\A\s*\z/
  end

  def asking_question?(message)
    message =~ /\?\z/
  end

  def shouting?(message)
    message.gsub(/[^A-Za-z]/, '') =~ /\A[A-Z]+\z/
  end
end
