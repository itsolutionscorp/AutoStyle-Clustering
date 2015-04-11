class Bob

  attr_accessor :request

  def hey(remark)
    self.request = Request.new(remark)

    return 'Whoa, chill out!' if  request.yelling?
    return 'Sure.' if request.question?
    return 'Fine. Be that way!' if request.silence?
    return 'Whatever.'
  end
end

class Request < Struct.new(:phrase)
  def yelling?
    phrase.upcase == phrase && phrase.downcase != phrase
  end

  def question?
    phrase.end_with?('?')
  end

  def silence?
    true if phrase.strip.empty?
  end
end
