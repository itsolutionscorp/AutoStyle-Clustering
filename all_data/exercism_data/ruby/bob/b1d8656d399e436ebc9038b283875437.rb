class Bob
  def hey(message)
    inquirer = MessageInqirer.new(message)

    if inquirer.shouting?
      'Woah, chill out!'
    elsif inquirer.question?
      'Sure.'
    elsif inquirer.blank?
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end

  class MessageInqirer
    def initialize(message)
      @message = message
    end

    def question?
      @message.end_with?('?')
    end

    def shouting?
      !blank? and @message.chars.all? { |char| char == char.upcase }
    end

    def blank?
      @message.empty?
    end
  end
end
