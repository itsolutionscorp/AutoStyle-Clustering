class Bob
  def hey(statement)
    @message = Message.new(statement)

    respond
  end

  private

  def respond
    case
    when @message.silent?
      'Fine. Be that way!'
    when @message.yelled?
      'Woah, chill out!'
    when @message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(statement)
    @statement = statement.gsub(/\r|\n/, '').strip
  end

  def question?
    @statement.end_with?('?')
  end

  def silent?
    @statement.empty?
  end

  def yelled?
    @statement == @statement.upcase && @statement.match(/[A-Z]+/)
  end
end
