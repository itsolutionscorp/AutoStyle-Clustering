class Bob
  Silence = /^\s*$/
  Question = /\?$/
  Shouting = /^[ -`{-~]*$/
  # this uses two ranges to match any ASCII chars up to a-z (from <space> to `) and any after (from { to ~)

  def hey(message)
    case message

    when Silence then 'Fine. Be that way!'
    when Shouting then 'Woah, chill out!'
    when Question then 'Sure.'

    else
      'Whatever.'
    end
  end
end
