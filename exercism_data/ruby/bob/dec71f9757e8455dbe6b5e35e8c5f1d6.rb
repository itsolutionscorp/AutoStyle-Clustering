class Bob

  def initialize
  end

  def hey(content)
    if content.nil? || content.empty? 
      "Fine. Be that way."
    elsif content == content.upcase
      "Woah, chill out!"
    elsif content.chomp.reverse.start_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end

end
