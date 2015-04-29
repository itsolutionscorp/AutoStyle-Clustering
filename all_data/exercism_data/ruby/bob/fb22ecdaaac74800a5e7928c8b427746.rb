class Bob
  def hey(line)
    if line.end_with?('?')
      if line == line.upcase
        if line =~ /^[0-9,?\s]+$/
          return "Sure."
        else
          return 'Woah, chill out!'
        end
      elsif line =~ /^[0-9,?\s]+$/
        return ""
      else
        return 'Sure.'
      end
    elsif line.empty? || line.strip.empty?
      return 'Fine. Be that way!'
    elsif line =~ /^[0-9,\s]+$/
      return 'Whatever.'
    elsif line == line.upcase 
      return 'Woah, chill out!'
    else
      return 'Whatever.'
    end
  end
end
