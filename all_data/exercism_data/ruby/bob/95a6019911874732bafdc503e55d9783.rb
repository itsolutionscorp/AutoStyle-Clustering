class Bob
  def hey(string)
    string.gsub!("\n"," ")
    # All
    if string == string.upcase && !!string.match(/[a-zA-Z]/)
      'Woah, chill out!'
    elsif string.match(/\?$/)
      'Sure.'
    elsif string.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
