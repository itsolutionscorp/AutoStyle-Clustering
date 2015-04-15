class Bob
  def hey(hey_string)
    case hey_string
    when /\A[\d\WA-Z]+\Z/
      'Woah, chill out!'
    when /\A.*\?\Z/
      'Sure.'
    when /.*(!|.)/
      'Whatever.'
    when nil
      'Fine. Be that way.'
    when ''
      'Fine. Be that way.'
    end
  end
end
