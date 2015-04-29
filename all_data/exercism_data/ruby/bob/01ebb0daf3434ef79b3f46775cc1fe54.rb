class Bob

  def hey (phrase)
    phrase.gsub!(/[\n\r]/m, '')
    return "Fine. Be that way!" if phrase.gsub(/[\W]/,'') == ''
    return "Whoa, chill out!" if phrase.gsub(/[\W\d]/,'').match(/^[A-Z]+$/)
    return "Sure." if phrase.match( /\?$/ )
    return "Whatever."
  end

end
