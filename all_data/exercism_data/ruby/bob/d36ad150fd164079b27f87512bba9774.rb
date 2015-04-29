class Bob
  def hey message
    statement = Statement.new message

    if statement.silence?
      'Fine. Be that way!'
    elsif statement.question?
      'Sure.'
    elsif statement.yelling?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class Statement
  attr_reader :statement

  def initialize message
    @statement = message
  end

  def yelling?
    return if silence?
    statement.upcase == @statement
  end

  def question?
    return if yelling?
    statement.end_with? '?'
  end

  def silence?
    statement.strip.empty?
  end
end
