class Bob
  def hey(words)
    if words.strip.empty? || words.strip.nil?
      'Fine. Be that way!'
    elsif words == words.upcase
      if words =~ /\d/ && (words.end_with? ("?"))
        'Sure.'
      elsif words =~ /\d/ && ((words.end_with? *['?', '!']) == false)
        'Whatever.'
      else
        'Whoa, chill out!'
      end
    elsif (words.end_with? ('?'))
      'Sure.'
    else
      'Whatever.'
    end
  end
end
