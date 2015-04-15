class Bob

  def hey(string)

    if string.match(/\A[^a-z]*[A-Z][^a-z]*\z/)
    "Woah, chill out!"
    elsif string.match(/^[\d,\s][\?!]*$/)
      "Sure."
    elsif string.match(/^[a-zA-Z].+\?$/)
      "Sure."
    else
      "Whatever."
    end

  end
end
