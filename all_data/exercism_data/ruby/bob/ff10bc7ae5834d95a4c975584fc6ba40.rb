class Bob
  def hey(inputRemark)
    remark = Remark.new(inputRemark)
    if remark.yell?
      return "Whoa, chill out!"
    elsif remark.question?
      return "Sure."
    elsif remark.silence?
      return "Fine. Be that way!"
    else
      return "Whatever."
    end
  end
  
  
end

class Remark < String
  def initialize(remark)
    @remark = remark
  end
  def yell?
    (@remark.upcase == @remark) && !(@remark.match(/[A-Z]/).nil?) && (@remark != "I?")
  end
  def question?
    @remark.split(//).last == "?"
  end
  def silence?
    @remark.sub(/\s+/,"") == ""
  end
end
