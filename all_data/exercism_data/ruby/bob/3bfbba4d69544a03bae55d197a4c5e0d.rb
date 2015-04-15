class Bob

  DEFAULT_RESPONSE = 'Whatever.'

  def hey(remark)
    @interpreter = RemarkInterpreter.new(remark)
    return 'Fine. Be that way!'   if @interpreter.silent?
    return handle_numbers(remark) if @interpreter.numbers?
    return handle_shouting_or_questions(remark)
    default_response
  end

  private

  def handle_numbers(remark)
    return handle_shouting_or_questions(remark)
    default_response
  end

  def handle_shouting_or_questions(remark)
    if @interpreter.shouting? || @interpreter.forceful_numbers?
      return 'Whoa, chill out!'
    end
    return 'Sure.' if @interpreter.question?
    default_response
  end

  def default_response
    DEFAULT_RESPONSE
  end
end

class RemarkInterpreter

  attr_reader :remark

  def initialize(remark)
    @remark = remark
  end

  def numbers?
    remark.match(/\d/)
  end

  def question?
    remark.to_s.end_with?('?')
  end

  def silent?
    remark.gsub(/\s/,'').empty?
  end

  def shouting?
    remark.match(/^(?!.*[a-z])(?!.*[0-9]).+$/)
  end

  def forceful_numbers?
    numbers? && remark.to_s.end_with?('!')
  end

end
