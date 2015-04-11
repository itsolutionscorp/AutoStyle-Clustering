class Bob
  def hey(input)
    case input
    when /^( )*$/ then "Fine. Be that way!"
    when input.upcase then "Woah, chill out!"
    when /.*\?$/ then "Sure."
    else 'Whatever.'
    end
  end
end
