class Bob

  def chatting?
     ! (shouting? || questioning? || silent_treatment?)
  end
  
  def silent_treatment?
    @heard.nil? || @heard .empty?
  end

  def questioning?
    @heard.end_with?('?') 
  end

  def shouting?
    @heard == @heard.upcase 
  end

  def responders
    [ Proc.new { "Fine. Be that way." if silent_treatment? }, 
      Proc.new { "Sure." if questioning? }, 
      Proc.new { "Woah, chill out!" if shouting? }, 
      Proc.new { "Whatever." if chatting? },
    ]
  end

  def hey(statement)
    @heard = statement
    responders.inject(false) { |result,responder|  gather_response(result, responder) }
  end

  def gather_response(result, responder)
    result ? result : responder.call
  end
 
end
