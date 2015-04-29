class Sentence

  attr_reader :sentence
  def initialize(sentence)
  @sentence = sentence.gsub(/\n/,' ')
  end

  def match
    case
    when statement_is_forceful then 'Woah, chill out!'
    when statement_is_a_question then 'Sure.'
    when statement_is_empty then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  private
  def statement_is_forceful
    sentence.match(/^[A-Z\s]*[0-9\W]*[A-Z]+[0-9A-Z\?!\s]*$/)
  end

  def statement_is_a_question
    sentence.match(/\?$/)
  end

  def statement_is_empty
    sentence.match(/^\W*$/)
  end
end

class Bob

  def hey(statement)
    s = Sentence.new(statement)
    s.match
  end
end
