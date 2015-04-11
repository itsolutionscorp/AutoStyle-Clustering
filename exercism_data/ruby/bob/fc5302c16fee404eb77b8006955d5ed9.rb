class Bob
  def hey(input)
    case(classify input)
    when :question
      "Sure."
    when :yelling
      "Woah, chill out!"
    when :nothing
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

private

  def classify(input)
    if empty? input
      # this has to come before the test for all caps (since an empty string is
      # technically all caps)
      :nothing
    elsif all_caps? input
      :yelling
    elsif question? input
      # this has to come after the test for all caps (since the tests require
      # forceful questions to count as yelling)
      :question
    else
      :default
    end
  end

  def empty? input
    # is the input all whitespace?
    input.strip.empty?
  end

  def all_caps? input
    # are all capitalizable characters capitalized?
    input.upcase == input
  end

  def question? input
    # is the last character a question mark?
    input[-1] == '?'
  end
end
