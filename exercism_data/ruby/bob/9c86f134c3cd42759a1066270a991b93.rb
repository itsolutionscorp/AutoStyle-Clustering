class Bob
  def hey arg
    t = arg
    if t.gsub(/\p{^Alpha}/, '') == t.gsub(/\p{^Alpha}/, '').upcase && t.gsub(/\p{^Alpha}/, '') != ''
      "Woah, chill out!"
    elsif arg.end_with? '?'
      "Sure."
    elsif arg =~ /^\s+$/ || arg == ''
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
