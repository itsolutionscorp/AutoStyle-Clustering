class Bob
def hey x
return "Fine. Be that way!" if x.strip==""
return "Woah, chill out!" if x==x.upcase and x!=x.downcase
return "Sure." if x.end_with? "?"
"Whatever."
end
end
