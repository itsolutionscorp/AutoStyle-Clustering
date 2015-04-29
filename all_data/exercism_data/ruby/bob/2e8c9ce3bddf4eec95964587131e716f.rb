class Bob

  def hey(communication)

    @communication_stripped = communication.gsub(/[^a-zA-Z]/, '')
    @silence_check = communication.gsub(/\s+/, "")

    if  is_yelling?(communication)
      'Woah, chill out!'

    elsif is_question?(communication)
      'Sure.'

    elsif is_silence?(communication)
      'Fine. Be that way!'

    else
      "Whatever."
    end

  end

  private

  def is_yelling?(str)
    # if the string is not empty, remove non-alpha characters and check for all caps
    (!@communication_stripped.empty?) && (@communication_stripped == @communication_stripped.upcase)
  end

  def is_question?(str)
    str.end_with?("?")
  end

  def is_silence?(str)
    str.empty? || @communication_stripped.empty? && @silence_check.empty?
  end


end
