class Bob
  def hey(remark)
    @remark = remark
    if /\A\s*\z/.match(@remark)
      "Fine. Be that way!"
    elsif /\A[^a-zA-Z]/.match(@remark) != nil && ! @remark.end_with?("?") && ! @remark.end_with?("!")
      "Whatever."
    elsif /\A[^a-zA-Z]/.match(@remark) != nil && @remark.end_with?("?")
      "Sure."
    elsif @remark.upcase == @remark
      "Woah, chill out!"
    elsif @remark.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
