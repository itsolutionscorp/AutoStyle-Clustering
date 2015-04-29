class Bob 
  def hey(msg)
    if isSilence? msg
      return 'Fine. Be that way!'
    end

    if isYelling? msg
      return 'Woah, chill out!'
    end

    if isAsking? msg
      return 'Sure.'
    end

    'Whatever.'
  end

  def isSilence?(msg)
    msg.strip == ''
  end

  def isYelling?(msg)
    msg.upcase == msg
  end

  def isAsking?(msg)
    msg.end_with? '?'
  end 

  private :isSilence?, :isYelling?, :isAsking?
end
