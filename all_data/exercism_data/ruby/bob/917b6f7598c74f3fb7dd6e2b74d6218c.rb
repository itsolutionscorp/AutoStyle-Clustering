class Bob

  def is_number?(item)
    return true if item =~ /^\d+$/
  end
  def is_numbers_only?(text)
    array = text.split(',').collect { |item| item.strip }
    array.all? { |item| is_number?(item) }
  end
  def is_upcase?(text)
    text == text.upcase
  end
  def strip_q(text)
    #input  - "Hello? "
    #output - "Hello" 
    text.strip[0..-2]
  end
  def empty?(text)
    text.strip.empty?
  end

  WHATEVER = "Whatever."
  CHILL = "Woah, chill out!"
  SURE = 'Sure.'
  FINE = "Fine. Be that way!"

  def hey(text)
    if empty?(text)
      FINE
    elsif is_numbers_only?(text)
      WHATEVER
    elsif text.end_with?('?')
      if is_numbers_only?(strip_q(text))
        SURE
      elsif is_upcase?(text)
        CHILL
      else
        SURE
      end
    elsif text.upcase == text
      CHILL
    else
      WHATEVER
    end
  end
end
