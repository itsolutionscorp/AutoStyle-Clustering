class Bob
  def hey(remark)
    if remark.empty_message?
      "Fine. Be that way!"
    elsif remark.shouty?
      "Whoa, chill out!"
    elsif remark.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class String
  def empty_message?
    self.tr("\n", "") =~ /^[\s]*$/
  end

  def question?
    to_bobspeak.end_with?("?")
  end

  def shouty?
    to_bobspeak == to_bobspeak.upcase && /[A-Za-z]+/ =~ to_bobspeak
  end

  private
  def to_bobspeak
    tr("\n","").gsub(/[^!?A-Za-z ]/, "")
  end
end
