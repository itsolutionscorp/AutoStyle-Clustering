# class Bob
#   def hey statement
#     if statement.upcase == statement && statement.upcase != statement.downcase
#       "Whoa, chill out!"
#     elsif statement[-1] == "?"
#       "Sure."
#     else
#       "Whatever."
#     end
#   end
# end

#this code passes up  to line 79
# class Bob
# def hey statement
#    if statement.upcase == statement && statement.upcase != statement.downcase
#      "Whoa, chill out!"
#     elsif statement[-1] == "?"
  #     "Sure."
    # elsif statement[0..100] == ""
      # "Fine. Be that way!"
    # else
      # "Whatever."
    # end
  # end
# end


class Bob
  def hey statement
    if statement.upcase == statement && statement.upcase != statement.downcase
      "Whoa, chill out!"
    elsif statement[-1] == "?"
      "Sure."
    elsif statement.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
