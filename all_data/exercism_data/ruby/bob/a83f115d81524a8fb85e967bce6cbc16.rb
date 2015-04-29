class Bob
  def hey(sentence)
    case sentence
    when :nil?.to_proc, :empty?.to_proc
      "Fine. Be that way."
    when /\?\Z/ # ends with a question mark?
      "Sure."
    when /\A[^a-z]+\Z/ # it's all capital, numbers or symbols
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
