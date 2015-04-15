class Bob
  def hey(string)
    if string.upcase == string && string =~ /[A-z]/
      return 'Whoa, chill out!'
    elsif string.end_with?('?')
      return 'Sure.'
    elsif string.gsub(/\s/,"").empty?
      return "Fine. Be that way!"
    else
      return 'Whatever.'
    end
  end
end
