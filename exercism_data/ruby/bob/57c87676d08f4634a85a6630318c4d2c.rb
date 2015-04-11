class Bob
  def hey(prompt)
    if prompt == prompt.upcase && prompt != prompt.downcase
      return "Whoa, chill out!"
    end
    if prompt[-1] == "?"
        return "Sure."
    end
    if prompt.strip.empty?
      return "Fine. Be that way!"
    end
    "Whatever."
  end
end
