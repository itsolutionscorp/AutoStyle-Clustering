class Bob
  def hey(prompt)
    return case prompt
    when /\A\s*\Z/     then "Fine. Be that way!"
    when /\A[^a-z]*\Z/ then "Woah, chill out!"
    when /\?\Z/        then "Sure."
    else                    "Whatever."
    end
  end
end
