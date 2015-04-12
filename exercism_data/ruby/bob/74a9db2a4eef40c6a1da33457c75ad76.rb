class Bob
  def hey(greeting)
    if is_empty?(greeting)
      "Fine. Be that way!"
    elsif is_shouted?(greeting)
      "Woah, chill out!"
    elsif is_question?(greeting)
      "Sure."
    else
      "Whatever."
    end
  end

private
  def is_empty?(greeting)
    greeting.rstrip.empty?
  end

  def is_all_numbers?(greeting)
    greeting.match(/[^0-9\s[:punct:]]/).nil?
  end

  def is_question?(greeting)
    greeting.end_with?('?')
  end

  def is_shouted?(greeting)
    greeting.upcase == greeting && !is_all_numbers?(greeting)
  end
end