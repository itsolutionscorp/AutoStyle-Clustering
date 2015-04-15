class Bob
  def hey(statement)
    return 'Fine. Be that way!' if statement.strip == ''

    return 'Whoa, chill out!' if (statement.gsub(/[\d,\s?]/, '').upcase == statement.gsub(/[\d,\s?]/,'')) and (statement.gsub(/[\d,\s?]/,'') != '')

    return 'Sure.' if statement.end_with?('?')

    'Whatever.'
  end
end
