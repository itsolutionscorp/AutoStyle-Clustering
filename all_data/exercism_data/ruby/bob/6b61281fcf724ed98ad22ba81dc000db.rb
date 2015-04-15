class Bob
  def hey(speak)
    if speak.nil?
      "Fine. Be that way!"
    elsif speak =~ /^.*\?$/
      "Sure"
    elsif speak == speak.upcase
      "Whoa, chill out!"
    else
      "Whatever."
    end
  end
end
