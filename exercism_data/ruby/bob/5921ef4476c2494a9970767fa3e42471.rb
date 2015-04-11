class Bob
  AGE = :teenager

  def hey(sentence)
    Dialog.new(sentence).respond_as(AGE)
  end
end

class Dialog
  def initialize(sentence)
    @sentence = sentence
  end

  def respond_as(age)
    case age
    when :teenager
      TeenagerResponse.new(@sentence).answer
    end
  end
end

class TeenagerResponse
  RESPONSE = {
    :default  => "Whatever.",
    :yelling  => "Woah, chill out!",
    :question => "Sure.",
    :silence  => "Fine. Be that way!"
  }

  def initialize(sentence)
    @sentence = sentence
    @parser = SentenceParser.new(sentence)
  end

  def answer
    RESPONSE[kind_of_answer]
  end

  private

  def kind_of_answer
    return :silence if @parser.empty?
    return :yelling if @parser.yelling?
    return :question if @parser.question?
    :default
  end
end

class SentenceParser
  def initialize(sentence)
    @sentence = sentence
  end

  def yelling?
    @sentence.upcase == @sentence  
  end

  def empty?
    @sentence.nil? || @sentence.strip.empty?
  end

  def question?
    @sentence.end_with? "?"
  end
end
