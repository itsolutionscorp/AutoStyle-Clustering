class Bob
  def hey(input)
    case input
    when silence?  then "Fine. Be that way!"
    when shouting? then "Woah, chill out!"
    when question? then "Sure."
    else 'Whatever.'
    end
  end

  private
  def silence?
    ->(input) { input.strip.empty? }
  end

  def shouting?
    ->(input) { input == input.upcase }
  end

  def question?
    ->(input) { input.end_with? "?" }
  end
end
