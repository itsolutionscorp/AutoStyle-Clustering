class Bob
  def hey(text)
    case text
      when /\A *\Z/ then "Fine. Be that way!"
      when /\A[^a-z]*\Z/ then 'Woah, chill out!'
      when /\?\Z/ then 'Sure.'
      else "Whatever."
    end
  end
end
