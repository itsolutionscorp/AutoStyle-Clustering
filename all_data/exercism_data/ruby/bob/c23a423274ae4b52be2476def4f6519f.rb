class Bob

  def hey(statement = nil)
    adjust(statement)

    case
    when blank?(statement)
      'Fine. Be that way!'
    when statement =~ /\w/ && statement.upcase == statement
      (statement =~ /\d/) ? self.hey(statement.gsub(/\d/, '')) : 'Woah, chill out!'
    when statement =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  # inspired by rails blank? method
  def blank?(val)
    val.respond_to?(:empty?) ? val.empty? : !val
  end

  # flatten string to one line and remove leading and trailing spaces
  def adjust(val)
    val.gsub!(/\n/, ' ')
    val.strip!
  end

end
