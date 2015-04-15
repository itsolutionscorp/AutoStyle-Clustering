class Bob

  def hey(request)
    if request =~ /\A\W*\Z/
      "Fine. Be that way!"
    elsif request =~ /[A-Z]/ && request.upcase == request
      "Whoa, chill out!"
    elsif request =~ /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end

end
