module Teenager
  def hey(remark)

    if has_no_text? remark
      "Fine. Be that way!"
    elsif is_uppercase_only?(remark)
      "Whoa, chill out!"
    elsif remark.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def has_letters?(remark)
    remark =~ /[A-Za-z]/
  end

  def is_uppercase_only?(remark)
    remark.upcase == remark && has_letters?(remark)
  end

  def has_no_text?(remark)
    remark.gsub(/\s+/, "").length == 0
  end

end

class Bob
  include Teenager
end
