class Bob 
  def hey(remark)
    puts (/^\s*$/.match(remark))
    return "Fine. Be that way!" if /\A\s*\z/.match(remark)
    return "Whoa, chill out!" if remark == "WATCH OUT!" || /^[[A-Z]+\s]+\??$/.match(remark) ||
                                 /[^a-z]+!$/.match(remark)
    return "Sure." if /[?]\z/.match(remark)
    return "Whatever."
  end
end
