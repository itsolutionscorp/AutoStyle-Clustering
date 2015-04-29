##
# Bob the lacksidaisical teenager
class Bob

  def hey(msg)
    return "Fine. Be that way!" if nothing_said?(msg)

    case
    when shout?(msg)
      "Woah, chill out!"
    when question?(msg)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def nothing_said?(msg)
    msg.nil? || msg.strip == ''
  end

  def question?(msg)
    msg.end_with?("?")
  end

  def shout?(msg)
    msg.upcase == msg && /[a-zA-Z]/ =~ msg
  end
end
