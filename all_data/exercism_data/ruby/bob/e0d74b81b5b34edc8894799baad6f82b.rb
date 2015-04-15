class Bob
  def hey what
    case what.to_s.strip
    when '' then 'Fine. Be that way!'
    when /^[^a-z]+$/ then 'Woah, chill out!'
    when /\?$/ then 'Sure.'
    else 'Whatever.'
    end
  end
end
