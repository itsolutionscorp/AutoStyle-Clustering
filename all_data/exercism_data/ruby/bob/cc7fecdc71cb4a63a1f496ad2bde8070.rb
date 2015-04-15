class Bob
  attr_reader :remark

  def hey(remark)
    @remark = remark
    if question
      "Sure."
    elsif shout
      "Whoa, chill out!"
    elsif silence
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
  
  def words
    remark.chars
  end

  def question
    !words.all?{|e| e.match(/[A-Z\s\?]/)} && remark.match(/\?\z/)
  end

  def shout
    words.all? {|e| e == e.upcase } && words.any? {|e| e.match(/[a-zA-Z]/)}
  end

  def silence
    words.all? {|char| char == " " } || (remark == "" || remark.include?("\t"))
  end
end
