class Bob

  def hey(input)
    conversation = Conversation.new(input)

    case
    when conversation.silent?
      return "Fine. Be that way!"
    when conversation.yelling? 
      return "Woah, chill out!"
    when conversation.question? 
      return "Sure."
    else
      return "Whatever."
    end
  end
end

class Conversation

  def initialize(input)
    @input = input
  end

  def silent?
    input.strip.empty? 
  end

  def yelling?
    input.upcase == input && input.downcase != input
  end

  def question?
    input.strip.end_with?('?') 
  end

  private

  attr_reader :input
end
