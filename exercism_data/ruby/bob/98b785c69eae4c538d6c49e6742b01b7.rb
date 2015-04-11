class Bob
  def hey(words)
    words = words.gsub(/[0-9]/, "")
    if words =~ /^\s*$/
      'Fine. Be that way!'
    elsif words[-1].chr == '?' && words != words.upcase
      'Sure.'
    elsif words == words.upcase
      'Whoa, chill out!'
    else
      'Whatever.'
    end
  end
end
