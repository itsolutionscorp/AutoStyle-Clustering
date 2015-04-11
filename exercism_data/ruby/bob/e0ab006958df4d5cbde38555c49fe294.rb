class Bob

  ALL_CAPS = lambda {|sentence| sentence == sentence.upcase}
  QUESTION = lambda {|sentence| sentence.match(/\?\z/)}
  NOTHING  = lambda {|sentence| sentence.match(/\A\s*\z/)}

  def hey( input )
    case input
    when NOTHING
      "Fine. Be that way!"
    when ALL_CAPS
      "Woah, chill out!"
    when QUESTION
      "Sure."
    else
      "Whatever."
    end
  end

end
