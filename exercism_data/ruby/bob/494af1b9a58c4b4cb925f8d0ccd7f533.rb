class Bob
  def hey(words)
    if getting_the_silent_treatment?(words)
      'Fine. Be that way!'
    elsif being_shouted_at?(words)
      'Woah, chill out!'
    elsif words[-1,1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def being_shouted_at?(words)
    words.upcase == words
  end

  def getting_the_silent_treatment?(words)
    words.to_s == '' || words =~ /^[*[:space:]$]/
  end
end
