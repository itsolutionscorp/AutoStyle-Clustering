class Bob
  def hey(remark)
    case
    when remark.upcase == remark && remark.match(/[A-Z]/) then "Whoa, chill out!"
    when remark[-1] == "?" then "Sure."
    when remark.strip == "" then "Fine. Be that way!"
    else
    "Whatever."
    end
  end
end
