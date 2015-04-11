class Bob
  def hey(greeting)
    if greeting.rstrip.empty?
      "Fine. Be that way!"
    elsif is_all_numbers?(greeting)
      if is_question?(greeting)
        "Sure."
      else
        "Whatever."
      end
    elsif is_shouted?(greeting)
      "Woah, chill out!"
    elsif is_question?(greeting)
      "Sure."
    else
      "Whatever."
    end
  end

private
  def is_all_numbers?(greeting)
    greeting.match(/[^0-9\s[:punct:]]/).nil?
  end
  def is_question?(greeting)
    greeting.end_with?('?')
  end

  def is_shouted?(greeting)
    greeting.upcase == greeting
  end
end
