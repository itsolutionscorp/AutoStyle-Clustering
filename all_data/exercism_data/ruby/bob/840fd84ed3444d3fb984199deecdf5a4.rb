class Bob
  def hey(phrase)
    empty    = :empty?.to_proc
    caps     = -> string { !/\p{lower}/.match(string) }
    question = -> string { /\?\s*$/.match(string) }

    case phrase.strip
    when empty    then "Fine. Be that way!"
    when caps     then "Woah, chill out!"
    when question then "Sure."
    else               "Whatever."
    end
  end
end
