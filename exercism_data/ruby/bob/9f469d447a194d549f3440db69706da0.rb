class Bob
  def hey(something)
    case something
    when '', nil
      'Fine. Be that way!'
    when /^[^a-z]+$/
      "Woah, chill out!"
    when /\?$/
      'Sure.'
    else
      "Whatever."
    end
  end
end
