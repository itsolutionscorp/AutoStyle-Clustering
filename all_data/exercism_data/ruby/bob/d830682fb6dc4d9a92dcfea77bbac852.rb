class Bob
  def hey message
    response.each { |k, v| return v if k =~ message.to_s }
  end

  def response
    @response ||= {
      /\A\z/ => "Fine. Be that way.",
      /\A[^a-z]+\z/ => "Woah, chill out!",
      /\?\z/ => "Sure.",
      /.+/ => "Whatever."
    }
  end
end
