class Bob
  def hey statement
    case
      when statement.nil?, statement.empty?
        'Fine. Be that way.'
      when statement.upcase == statement
        'Woah, chill out!'
      when statement.end_with?('?')
        "Sure."
      else 'Whatever.'
    end
  end
end
