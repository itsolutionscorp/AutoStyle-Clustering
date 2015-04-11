def hey(s):
  if not s.strip():
    return "Fine. Be that way!"
  elif any(_.isupper() for _ in s) and not any(_.islower() for _ in s):
    return "Whoa, chill out!"
  elif s[-1] == "?":
    return "Sure."
  else:
    return "Whatever."
