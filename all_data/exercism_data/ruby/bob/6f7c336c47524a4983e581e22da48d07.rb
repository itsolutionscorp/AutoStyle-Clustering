class Bob

  class Input
    def initialize(message)
      @message = message
    end

    def question?
      @message.end_with?('?')
    end

    def yelled?
      @message.upcase == @message
    end

    def meaningless?
      @message.to_s.empty?
    end
  end

  def hey(message)
    input = Input.new(message)

    if    input.meaningless? then 'Fine. Be that way.'
    elsif input.yelled?      then 'Woah, chill out!'
    elsif input.question?    then 'Sure.'
    else                          'Whatever.'
    end
  end

end
