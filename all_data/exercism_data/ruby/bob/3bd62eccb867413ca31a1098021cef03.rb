class Bob
  def hey(q)
    q.chomp!
    case
    when q.end_with?('?')
      "Sure."
    when q.empty?
      "Fine. Be that way!"
    when q == q.upcase # FIXME: locale-insensitive, memory wasting cop-out
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
