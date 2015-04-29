class Bob

  def hey(remark)
    if shouting?(remark)
      "Whoa, chill out!"
    elsif remark.chars.last == "?"
      "Sure."
    elsif silent?(remark)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

    def shouting?(remark)
      filtered_sentence = remark.split(/[^a-zA-Z0-9']+/).reject{ |w| w.match(/\d/) }.join
      filtered_sentence == filtered_sentence.upcase unless silent?(filtered_sentence.strip)
    end

    def silent?(remark)
      remark.strip == ""
    end

end
