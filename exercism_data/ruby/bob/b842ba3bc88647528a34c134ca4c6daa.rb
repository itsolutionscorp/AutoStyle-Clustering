class Bob
  def hey(a_statement)

    statement = Statement.new(a_statement)

    if statement.silence?
      return 'Fine. Be that way!'
    elsif statement.screaming?
      return 'Woah, chill out!'
    elsif statement.question?
      return 'Sure.'
    end

    'Whatever.'
  end

end

class Statement

  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def silence?
    sentence.strip.empty?
  end

  def question?
    sentence.end_with? '?'
  end

  def screaming?
    sentence == sentence.upcase && contains_letters(sentence)
  end

  private
  def contains_letters(sentence)
    !(sentence =~ /^((?![a-zA-Z]).)*$/)
  end

end
