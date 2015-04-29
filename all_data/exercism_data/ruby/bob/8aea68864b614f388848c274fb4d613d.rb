class SentenceChecker
  attr_reader :sentence
  AVAILABLE_TYPES = %I(yelling question nothing)

  def initialize(sentence)
    @sentence = sentence
  end

  def yelling?
    sentence == sentence.upcase && sentence.tr('^A-Za-z', '').size > 0
  end

  def question?
    sentence[-1] == '?'
  end

  def nothing?
    sentence.nil? || sentence.strip.empty?
  end

  def sentence_type
    AVAILABLE_TYPES.each {|type| return type if public_send("#{type}?")}
  end

end

module LackadaisicalTeenager
  def hey(sentence)
    sentence_type = determine_sentence_type(sentence)
    if conversation_types.include?(sentence_type)
      specific_answers[sentence_type]
    else
      default_answer
    end
  end

  private

  def determine_sentence_type(sentence)
    sentence_checker = SentenceChecker.new(sentence)
    sentence_checker.sentence_type
  end

  def conversation_types
    specific_answers.keys
  end

  def specific_answers
    {
      yelling: 'Woah, chill out!',
      question: 'Sure.',
      nothing: 'Fine. Be that way!'
    }
  end

  def default_answer
    'Whatever.'
  end
end

class Bob
  include ::LackadaisicalTeenager
end
