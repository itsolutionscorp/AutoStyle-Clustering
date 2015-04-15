class Bob

  def hey(statement)
    return 'Fine. Be that way!' unless statement.strip.length > 0
    statement = strip_acronyms(statement)
    if statement =~ /\A.*[A-Z]+[^a-zA-Z.]*[?!]?\z/
      'Woah, chill out!'
    elsif statement[-1] == '?'
      'Sure.'
    else
      "Whatever."
    end
  end

  def strip_acronyms(statement)
    statement = statement.dup
    %w(OK DMV).each { |acronym| statement.gsub!(/#{acronym}/,'')}
    statement
  end
end
