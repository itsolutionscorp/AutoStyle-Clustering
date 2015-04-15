class Bob
  MATCH = [
    { exp: /\A\s*\Z/, msg: "Fine. Be that way!" },
    { exp: /\A[\d,\.\s!]+\Z/, msg: "Whatever." },
    { exp: /\A[A-Z\d,\.\s!]+\Z/, msg: "Whoa, chill out!" },
    { exp: /\A[A-Z\s!]+(\?|)\Z/, msg: "Whoa, chill out!" },
    { exp: /\A[\w\.,\s!]+\?\Z/, msg: "Sure." },
    { exp: /\A[\d\w,\.\s!'\-\?]+\Z/, msg: "Whatever." }
  ]

  def hey(remark)
    MATCH.each { |e| return e[:msg] if remark.match(e[:exp]) }
    "Whoa, chill out!"
  end
end
