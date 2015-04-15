class Bob
  def hey(msg)
    case msg
      when /\A\s*\z/
        "Fine. Be that way!"
      when /\A[^a-z]+\z/
        'Woah, chill out!'
      when /\?\s*\z/
        'Sure.'
      else
       'Whatever.'
    end
  end
end
