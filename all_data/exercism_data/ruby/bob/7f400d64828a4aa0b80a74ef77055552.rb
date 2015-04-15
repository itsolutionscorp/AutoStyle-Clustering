class Bob
  def hey speech
    case speech
    when nil, /\A\s*\z/ then 'Fine. Be that way!'
    when /\A[^a-z]+\z/ then 'Woah, chill out!'
    when /\?\z/ then 'Sure.'
    else "Whatever."
    end
  end

end
