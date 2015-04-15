class Bob
  def hey what
    what = what.gsub(/\d/, '')

    case
    when what.strip == '' then "Fine. Be that way!"
    when what =~ /\w/ && what == what.upcase then 'Woah, chill out!'
    when what[-1]['?'] then "Sure."
    else "Whatever."
    end
  end
end
