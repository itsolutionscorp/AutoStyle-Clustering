class Bob
  def hey statement
    case
    when shouting?(statement) then 'Woah, chill out!'
    when statement.end_with?("?") then "Sure."
    when statement.strip.empty? then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  def shouting? statement
    statement.match(/[a-zA-Z]/) and statement == statement.upcase
  end
end
