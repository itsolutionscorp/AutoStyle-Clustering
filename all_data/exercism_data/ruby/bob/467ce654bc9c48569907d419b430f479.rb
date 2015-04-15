class Bob
  def hey (expression)
    if expression.nil? || expression.strip == ''
      "Fine. Be that way!"

    elsif not /[A-Za-z]/.match(expression)
      if expression.end_with?('?')
        "Sure."
      else
        "Whatever."
      end

    elsif expression == expression.upcase
      "Woah, chill out!"

    elsif expression.end_with?('?')
      "Sure."

    else
      "Whatever."
    end
  end
end
