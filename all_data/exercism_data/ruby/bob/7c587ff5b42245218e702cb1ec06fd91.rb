class Bob
  class Dialogue
    def initialize(message)
      @message = message.strip
    end

    def shouted?
      !empty? && @message == @message.upcase
    end

    def question?
      @message.end_with? '?'
    end

    def empty?
      @message.empty?
    end
  end

  def hey(message)
    dialogue = Dialogue.new(message)
    case
    when dialogue.shouted?
      'Woah, chill out!'
    when dialogue.question?
      'Sure.'
    when dialogue.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
