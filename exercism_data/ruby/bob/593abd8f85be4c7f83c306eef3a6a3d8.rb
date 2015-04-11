class Bob

  def hey(remark)
    if yell?(remark)
      "Whoa, chill out!" 
    elsif question?(remark)
      "Sure."
    elsif empty?(remark)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

    def yell?(remark)
      remark.match(/[A-Z]+/) && !remark.match(/[a-z]+/)
    end

    def question?(remark)
      remark[-1] == '?'
    end

    def empty?(remark)
      remark.empty? || remark.match(/\A\s+\z/)
    end
end
