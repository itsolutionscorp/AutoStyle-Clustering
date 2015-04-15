class Bob
  def hey(remark)
    return "Fine. Be that way!"if isSilence(remark)
    return 'Whoa, chill out!' if isAllCaps(remark)
    return 'Sure.' if isQuestion(remark)
    return "Whatever."
  end

  def isQuestion(sentence)
    return true if sentence[-1]=='?'
  end

  def isSilence(sentence)
    return true if sentence.split(/\n/).collect {|l| '  ' + l}.join(" ")=~/^\s*$/
  end

  def isAllCaps(sentence)
    return true if (sentence == sentence.upcase) && sentence=~/\p{Alpha}/
  end
end
