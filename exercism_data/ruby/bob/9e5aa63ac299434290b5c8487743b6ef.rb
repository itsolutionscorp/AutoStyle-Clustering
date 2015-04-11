class Bob
  def hey(instruction)
    case instruction.strip
      when ''                 then "Fine. Be that way!"
      when instruction.upcase then "Woah, chill out!"
      when /\?$/              then "Sure."
      else "Whatever."
    end
  end
end
