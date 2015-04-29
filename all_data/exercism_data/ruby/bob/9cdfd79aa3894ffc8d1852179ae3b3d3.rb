class Bob

  def hey(communication)

    communication_stripped = communication.gsub(/[^a-zA-Z]/, '')
    silence_check = communication.gsub(/\s+/, "")

    if  (!communication_stripped.empty?) && (communication_stripped == communication_stripped.upcase)
      # if the string is not empty, remove non-alpha characters and check for all caps
      'Woah, chill out!'

    elsif communication.end_with?("?")
      'Sure.'

    elsif communication.empty? || communication_stripped.empty? && silence_check.empty?
      'Fine. Be that way!'

    else
      "Whatever."
    end

  end

end
