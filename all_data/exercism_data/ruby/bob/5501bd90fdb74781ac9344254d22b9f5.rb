class Bob

  def hey(input)
    if input.empty?
      "Fine. Be that way."
    elsif input.end_with?('?')
      "Sure."
    elsif input == input.upcase
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

#looks like the test was changed over to 'teenager' so I changed it back
#to Bob
