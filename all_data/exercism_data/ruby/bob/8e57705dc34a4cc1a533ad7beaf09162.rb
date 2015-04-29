class Bob
  def hey(phrase)
    case phrase
    when /\?$/ then 'Sure.'
    when nil, '' then 'Fine. Be that way.'
    when /^[^a-z]*$/ then 'Woah, chill out!'
    else 'Whatever.'
    end
  end
end
