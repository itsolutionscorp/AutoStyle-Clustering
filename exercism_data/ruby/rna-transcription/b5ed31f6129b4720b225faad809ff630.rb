class Bob
  def hey(original, sentence = Sentence.new(original))
    respond_to sentence
  end

  private

    def respond_to(sentence)
      case 
      when sentence.silence?  then "Fine. Be that way."
      when sentence.yelling?  then "Woah, chill out!"
      when sentence.question? then "Sure."
      else "Whatever."
      end
    end
end

class Sentence
  def initialize(message)
    self.message = message
  end

  def silence?
    message.to_s == ""
  end

  def yelling?
    message == message.upcase
  end

  def question?
    message.end_with? "?"
  end

  private 

    attr_accessor :message
end
