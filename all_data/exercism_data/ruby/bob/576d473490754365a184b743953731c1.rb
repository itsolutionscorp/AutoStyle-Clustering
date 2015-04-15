class Bob
  def hey(greeting)
    return "Fine. Be that way!" if greeting.nil? or greeting.empty?
    return "Woah, chill out!" if greeting.gsub(/[^a-zA-Z]/, '') =~ /\A[A-Z]+\z/
    return "Sure." if greeting.end_with?("?")

    # Everything else:
    "Whatever."
  end
end
