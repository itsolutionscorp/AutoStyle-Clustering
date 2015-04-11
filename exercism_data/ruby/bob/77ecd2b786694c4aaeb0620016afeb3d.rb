class Bob
  def hey(greeting)
    return "Fine. Be that way!" if silence?(greeting)
    return "Woah, chill out!" if yelling?(greeting)
    return "Sure." if question?(greeting)

    # Everything else:
    "Whatever."
  end

  private

  def silence?(greeting)
    greeting.nil? or greeting.empty?
  end

  def yelling?(greeting)
    greeting.gsub(/[^a-zA-Z]/, '') =~ /\A[A-Z]+\z/
  end

  def question?(greeting)
    greeting.end_with?("?")
  end
end
