class Bob
  def hey string
    if string.gsub(/\s|\d/, "").empty?
      'Fine. Be that way!'
    elsif string.match(/[A-Z]/) && !string.match(/[a-z]/)
      'Whoa, chill out!'
    elsif string[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
