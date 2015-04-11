class Bob
  def hey(saying)
    shout    = ->(saying) { saying.match(/[a-z]/i) && saying == saying.upcase }
    question = ->(saying) { saying.match(/\?\z/) }
    silence  = ->(saying) { saying.match(/\A\s*\z/) }

    case saying
    when shout
      "Woah, chill out!"
    when question
      "Sure."
    when silence
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
