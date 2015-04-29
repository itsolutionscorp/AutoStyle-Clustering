class Bob
  def hey input
    yelling  = /^[A-Z0-9\W]+$/
    question = /\?$/
    silence = /^$/

    case input.to_s
    when silence
      "Fine. Be that way!"
    when yelling
      "Woah, chill out!"
    when question
      'Sure.'
    else
      "Whatever."
    end
  end
end
