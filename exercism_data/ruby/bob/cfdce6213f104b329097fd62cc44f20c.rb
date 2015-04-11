class Bob
  def hey(response)
    if blank?(response)
      answers[:fine]
    elsif all_upper_case?(response)
      answers[:chillout]
    elsif question?(response)
      answers[:sure]
    else
      answers[:whatever]
    end
  end

  private

  def question?(response)
    response.end_with?('?')
  end

  def all_upper_case?(response)
    response == response.upcase
  end

  def blank?(response)
    response.to_s.strip.empty?
  end

  def answers
    {
      :whatever => "Whatever.",
      :chillout => "Woah, chill out!",
      :sure => "Sure.",
      :fine => "Fine. Be that way!"
    }
  end
end
