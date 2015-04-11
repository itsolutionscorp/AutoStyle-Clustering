class Bob
  def hey( s )
    clean = s.gsub( %r/\s+/, '' )
    case clean
    when %r/\?\Z/i
      "Sure."
    when %r/\A[0-9A-Z[:punct:]]+\Z/
      "Woah, chill out!"
    when %r/\A\Z/
      "Fine. Be that way."
    else
      "Whatever."
    end
  end
end
