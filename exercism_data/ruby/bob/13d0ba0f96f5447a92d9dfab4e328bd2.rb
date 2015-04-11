class Request

  def initialize(request)
    @request = request
  end

  def silent?
    @request.empty? || @request.match(/\A\s+\Z/)
  end

  def question?
    @request.end_with?('?')
  end

  def yelled?
    @request.gsub(/[\d\s,?]/,'').size > 0 && @request.upcase == @request
  end

end

class Bob
  def hey(message)
    request = Request.new(message)
    response = 'Whatever.'
    response = 'Fine. Be that way!' if request.silent?
    response = 'Sure.' if request.question?
    response = 'Woah, chill out!' if request.yelled?
    response
  end

end
