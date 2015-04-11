class Bob

  def hey(remark)
    case
    when silence?(remark)
      "Fine. Be that way!"
    when yelling?(remark)
      "Woah, chill out!"
    when remark.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(remark)
    remark.strip.empty?
  end

  def yelling?(remark)
    # if a lower case letter is found, it's not ALL_CAPS!
    !remark.match(/\p{Lower}/)
  end

end
