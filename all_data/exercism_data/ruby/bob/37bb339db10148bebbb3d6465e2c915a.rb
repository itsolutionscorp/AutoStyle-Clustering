class Bob
  def hey (statement)
    if statement == statement.upcase && statement.upcase != statement.downcase
      #second part of this: alternative way is regexp: if statement =~/[a-zA-Z]/
      "Whoa, chill out!"
    elsif statement.end_with?("?")
      "Sure."
    elsif statement.strip == ''
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
