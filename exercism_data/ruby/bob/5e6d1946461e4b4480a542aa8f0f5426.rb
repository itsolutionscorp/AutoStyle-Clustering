class Bob

  def hey(remark)
    remark.strip!

    if yell?(remark)
      "Whoa, chill out!" 
    elsif remark[-1] == '?'
      "Sure."
    elsif remark.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

    def yell?(remark)
      remark.match(/[A-Z]+/) && !remark.match(/[a-z]+/)
    end
end
