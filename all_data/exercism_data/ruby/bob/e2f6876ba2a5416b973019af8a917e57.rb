class Bob
  def hey(words)
    if words.strip.empty? || words.strip.nil?
      'Fine. Be that way!'
    elsif words == words.upcase
      if words =~ /\d/ && words[-1].chr == '?'
        'Sure.'
      elsif words =~ /\d/ && words[-1].chr != '?' && words[-1].chr != '!'
        'Whatever.'
      else
        'Whoa, chill out!'
      end
    elsif words[-1].chr == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
