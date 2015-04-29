class Bob
  def hey(remark)
    if silence?(remark)
      "Fine. Be that way!"
    elsif shout?(remark)
      "Whoa, chill out!"
    elsif question?(remark)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(remark)
    remark.strip.empty?
  end

  def shout?(remark)
    remark.upcase == remark && remark.downcase != remark
  end

  def question?(remark)
    remark.end_with?("?")
  end
end
