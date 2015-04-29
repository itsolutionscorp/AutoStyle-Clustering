class Bob

  def hey(words)
    conversation = Conversation.new(words)

    if conversation.nothing?
      'Fine. Be that way!'
    elsif conversation.yelled?
      'Woah, chill out!'
    elsif conversation.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

class Conversation

  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def yelled?
    @sentence == @sentence.upcase
  end

  def nothing?
    @sentence.strip.empty?
  end

  def question?
    @sentence.end_with? "?"
  end
end
