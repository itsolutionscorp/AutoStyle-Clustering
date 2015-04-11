class Bob
  def hey(statement)
    respond_with statement
  end

  private

  def respond_with statement
    return 'Whatever.' if statement.class != String

    if statement.strip.empty?
      return 'Fine. Be that way!'
    elsif statement.upcase === statement
      return 'Woah, chill out!'
    elsif statement.end_with?('?')
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
