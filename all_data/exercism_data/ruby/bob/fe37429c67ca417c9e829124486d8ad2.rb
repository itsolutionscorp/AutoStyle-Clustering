class Bob
  def hey(message)
    inquirer = MessageInquirer.new(message)

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

  class MessageInquirer
    def initialize(message)
      @message = message
    end

    def question?
      @message.end_with?('?')
    end

    def shouting?
      !blank? and @message.upcase == @message
    end

    def blank?
      @message.empty?
    end
  end
end
