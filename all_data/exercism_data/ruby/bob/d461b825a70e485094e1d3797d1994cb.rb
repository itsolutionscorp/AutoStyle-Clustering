class Bob

  def hey(said)
    if said.split("").last == '?'
      'Sure.'
    elsif said == said.upcase && said != ''
      'Woah, chill out!'
    elsif said == ''
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end

end
