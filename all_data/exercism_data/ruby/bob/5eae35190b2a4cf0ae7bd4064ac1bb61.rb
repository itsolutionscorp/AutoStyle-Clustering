class Bob

  def hey(words)

    if there_was_silence(words)
      'Fine. Be that way!'
    elsif someone_asked_question(words)
      'Sure.'
    elsif someone_shouted(words)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def there_was_silence(words)
    words.strip.empty?
  end

  def someone_asked_question(words)
    (words.end_with?('?')) && ((words.upcase != words) || (words.to_i > 0))
  end

  def someone_shouted(words)
    (words.upcase == words) && (words[-1].to_i < 1 )
  end
end
