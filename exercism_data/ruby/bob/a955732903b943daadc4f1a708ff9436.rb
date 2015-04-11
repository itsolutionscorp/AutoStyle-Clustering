class Bob
  def hey(query)
    @query = query
    case
    when uncertain?
      'Fine. Be that way!'
    when yelled_at?
      'Woah, chill out!'
    when questioned?
      'Sure.'
    else
      'Whatever.'
    end
  end

private
  def uncertain?
    @query = @query.strip
    @query.nil? || @query.empty?
  end

  def yelled_at?
    @query.upcase == @query
  end

  def questioned?
    @query.end_with?("?")
  end
end
