class Bob

  def hey(string)

    if string.match(^([\d,\s]+$)|([a-zA-Z]+[\.])|([a-zA-Z]+[\!])/)
      "Whatever."
    elsif string.match(/^[\d,\s][\?!]*$/)
      "Sure."
    elsif string.match(/^[A-Z\d\s,]+[\?!]*$/)
      "Woah, chill out!"
    elsif string.match(/^[a-zA-Z].+\?$/)
      "Sure."
    #elsif string.match(/^[A-Z].+!$/)
    #  "Whatever."
    end

  end
end
