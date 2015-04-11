class Bob
  def hey message
    response[message.to_s]
  end

  def response
    @response ||= RegexHash[
      /\A\z/ => "Fine. Be that way.",
      /\A[^a-z]+\z/ => "Woah, chill out!",
      /\?\z/ => "Sure.",
      /.+/ => "Whatever."
    ]
  end
end

class RegexHash < Hash
  def // string
    each { |regex, value| return value if regex =~ string }
  end
end
