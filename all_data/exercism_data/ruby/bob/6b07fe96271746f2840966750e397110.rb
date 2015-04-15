class Bob

  def hey(remark)
    case
    when silence?(remark)
      "Fine. Be that way!"
    when all_caps?(remark)
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

  def all_caps?(remark)
    !remark.match(/\p{Lower}/)
  end

end
