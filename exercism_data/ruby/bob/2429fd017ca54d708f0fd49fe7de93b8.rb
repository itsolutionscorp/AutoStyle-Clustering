class Bob

  CHILL = "Woah, chill out!"
  SURE = "Sure."
  FINE = "Fine. Be that way!"

  def hey(message)
    response = "Whatever."

    if shouting?(message) && !number_list?(message)
      response = CHILL
    elsif question?(message)
      response = SURE
    elsif saying_nothing?(message)
      response = FINE
    end

    return response
  end

  def shouting?(message)
    message.upcase == message && !saying_nothing?(message)
  end

  def question?(message)
    message.end_with?("?")
  end

  def number_list?(message)
    is_number = true
    message.split(",").each do |el|
      el.gsub!("?","") if question?(el)

      if (Integer(el) rescue false) == false
        is_number = false
        break
      end
    end

    return is_number
  end

  def saying_nothing?(message)
    message.split.length == 0
  end
end
