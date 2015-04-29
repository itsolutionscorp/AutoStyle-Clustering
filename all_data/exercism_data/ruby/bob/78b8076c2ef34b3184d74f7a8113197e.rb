class SilentRemark
  def match?(text)
    text.strip.empty?
  end

  def response
    'Fine. Be that way!'
  end
end

class YelledRemark
  def match?(text)
    text == text.upcase
  end

  def response
    'Woah, chill out!'
  end
end

class QuestioningRemark
  def match?(text)
    text.end_with?('?')
  end

  def response
    'Sure.'
  end
end

class AnyRemark
  def match?(text)
    true
  end

  def response
    'Whatever.'
  end
end

class Bob
  def initialize(remarks_understood=default_remarks_understood)
    @remarks_understood = remarks_understood
  end

  def hey(remark_text)
    remark = @remarks_understood.find { |r| r.match?(remark_text) }
    remark.response
  end

  private
  def default_remarks_understood
    [ SilentRemark.new, YelledRemark.new, QuestioningRemark.new, AnyRemark.new ]
  end
end
