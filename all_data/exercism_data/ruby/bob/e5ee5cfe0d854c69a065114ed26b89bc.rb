class Bob

  def hey(message)
    if message.gsub(/ /, '') == '' 
      return "Fine. Be that way!"
    end

    if /[a-z]/.match(message) == nil && /[A-Z]/.match(message)
      return "Woah, chill out!"
    end

    if /\?$/.match(message.gsub(/\n/,''))
      return "Sure."
    end

    "Whatever."
  end
end
