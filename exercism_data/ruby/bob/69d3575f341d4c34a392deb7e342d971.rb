class Bob

  def hey(input)
    case 
      when /.*[a-ЯA-Я]+.*/ =~ input && input == input.upcase; return "Woah, chill out!"
      when input.end_with?("?"); return "Sure."
      when input.strip.empty?; return "Fine. Be that way!"
      else "Whatever."
    end
  end

end
