class Bob
  def shouting?
    ->(input) { input == input.upcase }
  end

  def silence?
    ->(input) { input.strip.empty? }
  end

  def question?
    ->(input) { input[-1] == "?" }
  end

  def hey(input)
    case input
    when silence?  then "Fine. Be that way!"
    when shouting? then "Woah, chill out!"
    when question? then "Sure."
    else 'Whatever.'
    end
  end
end
