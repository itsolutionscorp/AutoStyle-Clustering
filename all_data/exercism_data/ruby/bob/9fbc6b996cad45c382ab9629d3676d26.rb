class Bob

  def hey(words)
    if words == '' || words == nil
      return 'Fine. Be that way!'
    end

    if allcaps(words)
      'Woah, chill out!'
    elsif words.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def allcaps(words)
    words.split('').each do |i|
      if i.upcase != i
        return false
      end
    end
  end
end
