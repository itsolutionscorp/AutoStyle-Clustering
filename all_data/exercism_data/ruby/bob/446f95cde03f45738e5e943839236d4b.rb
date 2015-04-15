class Sentence < String
  QUESTION_MARK     = '?'
  EXCLAMATION_MARK  = '!'

  def yelling?
    all_in_upcase?
  end

  def question?
    self.end_with? QUESTION_MARK
  end

  def exclamation?
    self.end_with? EXCLAMATION_MARK
  end

  def silence?
    blank?
  end

  protected
  def all_in_upcase?
    self == self.upcase
  end

  # straight from ActiveSupport Core Extensions
  def blank?
    self !~ /[^[:space:]]/
  end
end


class Bob
  def hey words
    sentence = Sentence.new(words.to_s)
    reply_to sentence
  end

  private
  def reply_to sentence
    case
    when sentence.silence?  then reply_for_silence
    when sentence.yelling?  then reply_for_yelling
    when sentence.question? then reply_for_question
    else reply_with_default
    end
  end

  def reply_with_default
    'Whatever.'
  end

  def reply_for_yelling
    'Woah, chill out!'
  end

  def reply_for_question
    'Sure.'
  end

  def reply_for_silence
    'Fine. Be that way.'
  end
end
