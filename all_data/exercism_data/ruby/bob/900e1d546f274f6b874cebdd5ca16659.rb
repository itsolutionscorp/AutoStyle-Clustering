class Bob
  def hey(message)
    statment = Statement.new message
    if statment.screaming?
      'Woah, chill out!'
    elsif statment.question?
      'Sure.'
    elsif statment.nothing?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Statement
  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def screaming?
    has_words? && @sentence.upcase == @sentence
  end

  def question?
    @sentence.end_with?('?')
  end

  def nothing?
    @sentence.strip.empty?
  end

  private

  def has_words?
    @sentence =~ /[A-Za-z]/
  end
end
