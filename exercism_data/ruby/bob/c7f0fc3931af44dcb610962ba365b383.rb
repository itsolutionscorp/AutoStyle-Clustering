class Bob


  def hey(talk)

    @talk = talk

    if  shouting
      "Whoa, chill out!"
    elsif question
      "Sure."
    elsif silence
      'Fine. Be that way!'
    elsif talk
      "Whatever."
    else
    end
  end

  def shouting
    @talk == @talk.upcase && @talk =~ /[A-Z]/
  end

  def question
    @talk[-1,1] == '?'
  end

  def silence
    @talk.strip == ''
  end



end
