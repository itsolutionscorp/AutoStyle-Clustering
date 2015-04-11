class Bob

  def hey(remark_text)
    remark = Remark.new(remark_text)
    case
    when remark.is_shouted? && remark.contains_words?
      "Whoa, chill out!"
    when remark.ends_with_a_question_mark?
      'Sure.'
    when remark.is_silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Remark
  attr_reader :remark

  def initialize(remark_text)
    @remark = remark_text
  end

  def is_shouted?
    remark.upcase == remark
  end

  def contains_words?
    remark.index(/[a-zA-Z]+/)
  end

  def ends_with_a_question_mark?
    remark[-1] == '?'
  end

  def is_silence?
    remark.strip.empty?
  end
end
