class Bob
  def hey(input)
    @input = input
    return 'Fine. Be that way.' if snubbed?
    return "Sure."              if questioned?
    return "Woah, chill out!"   if yelled_at?
    return "Whatever."
  end

  protected

  def snubbed?
      @input.nil? || @input.empty?
  end

  def questioned?
    @input.end_with? '?'
  end

  def yelled_at?
    @input == @input.upcase
  end
end
