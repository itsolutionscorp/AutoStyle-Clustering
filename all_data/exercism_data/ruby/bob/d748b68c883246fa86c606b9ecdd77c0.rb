class Bob
  def hey(q)
    q.chomp!
    case
    when q.end_with?('?')
      "Sure."
    when q.empty?
      "Fine. Be that way!"
    when q.upcase?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

# yay Monkeypatching!
class String
  def upcase?()
    # FIXME: locale-insensitive, memory wasting cop-out
    self == self.upcase
  end
end
