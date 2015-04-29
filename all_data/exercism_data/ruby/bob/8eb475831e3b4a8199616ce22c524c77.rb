class Bob
  def hey(statement)
    silent = statement.split.empty?
    yelling = statement === statement.upcase && (statement.count "A-Z") > 0
    inquiring = statement.end_with?('?')

    case
    when yelling then 'Woah, chill out!'
    when silent then 'Fine. Be that way!'
    when inquiring then 'Sure.'
    else 'Whatever.'
    end
  end
end
