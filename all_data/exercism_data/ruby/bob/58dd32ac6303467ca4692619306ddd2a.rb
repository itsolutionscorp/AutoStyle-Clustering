class Bob
  def hey(saywhat)
    response = {
      :silent => "Fine. Be that way!",
      :yelling => "Woah, chill out!",
      :question => "Sure.",
      :misc => "Whatever."
    }

    puts "in bob::hey(): saywhat=#{saywhat}"
    case
    when saywhat.strip.empty?
      return response[:silent]
    when saywhat == saywhat.upcase()
      return response[:yelling]
    when saywhat.end_with?("?")
      return response[:question]
    else
      return response[:misc]
    end
  end
end
