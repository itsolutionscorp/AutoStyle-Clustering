class Bob
  def hey(statement)
    message = Message.new(statement)

    if message.silent?
      'Fine. Be that way!'
    elsif message.yelled?
      'Woah, chill out!'
    elsif message.question?
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
    !!@statement.match(/\?$/)
  end

  def silent?
    @statement.empty?
  end

  def yelled?
    @statement == @statement.upcase && @statement.match(/[A-Z]+/)
  end
end
