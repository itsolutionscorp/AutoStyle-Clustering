class Bob

  # def hey(remark)
  #   if response.upcase
  #     remark == 'Whoa, chill out!'
  #   elsif response << '?'
  #     remark == 'Sure.'
  #   else
  #     remark == 'Whatever.'
  #   end
  # end

  def hey(statement)
    # .strip removes spaces
    if statement.strip.empty?
      "Fine. Be that way!"
    elsif statement.upcase == statement && statement.downcase != statement
      "Whoa, chill out!"
    elsif statement.end_with("?")
      "Sure."
    else
      "Whatever."
    end
  end

end
