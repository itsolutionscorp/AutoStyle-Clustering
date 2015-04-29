class Bob
  def hey(value)
    if value[/\A\s*\Z/m]
      "Fine. Be that way!"
    elsif value == value.upcase && value[/[A-Z]/]
      'Woah, chill out!'
    elsif value[/\?\Z/]
      'Sure.'
    else
      'Whatever.'
    end
  end
end
