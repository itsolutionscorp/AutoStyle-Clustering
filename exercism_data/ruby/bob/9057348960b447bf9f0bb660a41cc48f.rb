class Bob
  def hey(greeting)
    case
      when greeting.strip.empty?          then "Fine. Be that way!"
      when greeting.upcase.eql?(greeting) then "Woah, chill out!"
      when greeting[-1].end_with?("?")    then "Sure."
      else                                     "Whatever."
    end
  end
end
