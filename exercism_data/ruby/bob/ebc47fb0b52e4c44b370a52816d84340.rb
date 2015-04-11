class Bob
  def hey(statement)
    respond_with statement.to_s.strip
  end

  private

  def respond_with statement
    if statement.empty?
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
