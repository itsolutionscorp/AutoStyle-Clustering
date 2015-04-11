# encoding: utf-8

class Bob
  def hey(message)
    AnswerOfBob.new(Message.new(message)).get
  end
end

class Message
  QUESTION_MARK = '?'

  def initialize(message)
    @message = message.strip
  end

  def empty?
    @message.empty?
  end

  def yelled?
    @message.upcase == @message
  end

  def question?
    @message.end_with? QUESTION_MARK
  end
end

class Answer
  def initialize(message)
    @message = message
  end

  def get
    # should be implemented by subclass
  end
end

class AnswerOfBob < Answer
  def get
    case
    when @message.empty?    then "Fine. Be that way!"
    when @message.yelled?   then "Woah, chill out!"
    when @message.question? then "Sure."
    else "Whatever."
    end
  end
end
