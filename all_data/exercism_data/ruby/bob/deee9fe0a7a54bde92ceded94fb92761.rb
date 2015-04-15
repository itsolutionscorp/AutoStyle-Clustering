class Bob

  def hey(s)
    if is_a_yell? s
      answer="Woah, chill out!"
    elsif is_a_question? s
      answer="Sure."
    elsif is_a_silence? s
      answer="Fine. Be that way!"
    else
      answer="Whatever."
    end

    return answer
  end
end

def is_a_question? s
  return ((not s.empty?) and (s[-1] == "?"))
end

def is_a_yell? s
  return (s =~ /[[:alpha:]]/ and s == s.upcase)
end

def is_a_silence? s
  return s.strip == ""
end
