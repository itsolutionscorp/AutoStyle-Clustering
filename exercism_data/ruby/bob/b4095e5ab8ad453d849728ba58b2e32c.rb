class Bob
  def hey text
    case text
      when /\A[^a-z]*[A-Z]+[^a-z]*\z/ then 'Woah, chill out!'
      when /\?\z/ then 'Sure.'
      when /\A\s*\z/ then 'Fine. Be that way!'
      else 'Whatever.'
    end
  end
end
