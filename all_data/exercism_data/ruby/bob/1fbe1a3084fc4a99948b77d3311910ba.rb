class Bob
  def hey(greeting)
    case
    when is_empty?(greeting)
      "Fine. Be that way!"
    when is_shouted?(greeting)
      "Woah, chill out!"
    when is_question?(greeting)
      "Sure."
    else
      "Whatever."
    end
  end

private
  def is_empty?(greeting)
    greeting.strip.empty?
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
