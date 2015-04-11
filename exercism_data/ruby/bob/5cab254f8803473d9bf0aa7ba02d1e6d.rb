class Bob  
  def hey(request)
    sentence = Sentence.new(request)
    
    if sentence.blank?
      'Fine. Be that way!'
    elsif sentence.shouting?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Sentence
  def initialize(request)
    @request = request
  end

  def blank?
    request == '' || request.match(/\A\s+\z/)
  end
  
  def shouting?
    request.upcase == request
  end
  
  def question?
     request.end_with? '?'
  end
  
  private
  
  attr_reader :request
end
