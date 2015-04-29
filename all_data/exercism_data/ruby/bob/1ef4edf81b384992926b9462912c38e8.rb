class Bob 
  def question?
    @request.match '\?\Z'
  end

  def yelling?
    @request.match '\A[A-Z\d\s[[:punct:]]]+\Z'
  end

  def silent?
    @request.match '\A\s*\Z'
  end

  def hey(q)
    @request = q
    if silent?
      'Fine. Be that way!'
    elsif yelling?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
