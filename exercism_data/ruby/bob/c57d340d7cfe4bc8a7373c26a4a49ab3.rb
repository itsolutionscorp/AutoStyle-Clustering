class Bob

def hey(message)
  if nothing? message
    answer = "Fine. Be that way!"
  elsif yelling? message
    answer = "Woah, chill out!"
  elsif question? message
    answer = "Sure."
  else
    answer = 'Whatever.'
  end
end

private
def question?(message)
  message.end_with?("?")
end

def yelling?(message)
  message =~ /\A[^a-z]*[A-Z][^a-z]*\z/
end

def nothing?(message)
  message.strip.empty?
end

end
