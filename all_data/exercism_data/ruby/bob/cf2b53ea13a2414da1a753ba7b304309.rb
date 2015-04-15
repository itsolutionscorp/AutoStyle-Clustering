class Bob

  def hey(query)
    case
    when silent?(query)
      'Fine. Be that way!'
    when yelling?(query)
      'Woah, chill out!'
    when questioning?(query)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def yelling?(query)
    query.upcase == query
  end

  def questioning?(query)
    query.end_with?('?')
  end

  def silent?(query)
    query.gsub(/\s+/, '').empty?
  end

end
