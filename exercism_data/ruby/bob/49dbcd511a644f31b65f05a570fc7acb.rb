class Bob
  def hey(remark)
    Response.new(remark).respond
  end
end

class Response
  def initialize(remark)
    @remark = remark
  end

  def respond
    if shout?
      'Whoa, chill out!'
    elsif question?
      'Sure.'
    elsif silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  attr_reader :remark

  def shout?
    (remark =~ /.*[a-z].*/i) == 0 && remark == remark.upcase
  end

  def question?
    remark.end_with?('?')
  end

  def silence?
    remark.strip.empty?
  end
end
