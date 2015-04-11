class Statement

  def initialize(heard)
    @heard = heard
  end

  def heard
    @heard ||= ''
    @heard
  end

  def chatting?
     ! (shouting? || questioning? || silent_treatment?)
  end
  
  def silent_treatment?
    heard.empty?
  end

  def questioning?
    heard.end_with?('?') 
  end

  def shouting?
    heard == heard.upcase 
  end

end

class Bob

  def responders
    [
      Proc.new { "Fine. Be that way." if @heard.silent_treatment? }, 
      Proc.new { "Sure." if @heard.questioning? }, 
      Proc.new { "Woah, chill out!" if @heard.shouting? }, 
      Proc.new { "Whatever." if @heard.chatting? },
    ]
  end

  def hey(statement)
    @heard = Statement.new(statement)
    responders.inject(nil) { |result,responder|  gather_response(result, responder) }
  end

  def gather_response(result, responder)
    result ? result : responder.call
  end
 
end
