class Bob
  def hey stmt
    case
    when shouting?(stmt) then 'Woah, chill out!'
    when stmt.end_with?("?") then "Sure."
    when stmt.strip.empty? then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  def shouting? stmt
    stmt.match(/[a-zA-Z]/) and stmt == stmt.upcase
  end
end
