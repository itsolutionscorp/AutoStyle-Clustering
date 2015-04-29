class Bob
  def hey(string)
    return 'Fine. Be that way!' if string.gsub(' ', '').empty?
    return 'Woah, chill out!'   if string.upcase == string &&
                                   string.downcase != string
    return 'Sure.'              if string[-1] == '?' 
    return 'Whatever.'
  end
end
