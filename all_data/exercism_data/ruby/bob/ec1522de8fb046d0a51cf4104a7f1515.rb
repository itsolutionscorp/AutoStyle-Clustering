# Am I doing it right? :)
Bob = Hash.new do |h,salutation|
  res = h.detect do |match, response|
    salutation =~ match
  end
  if res
    res.last
  else
    "Whatever."
  end
end

def Bob.new
  self
end

def Bob.hey(k)
  self[k]
end

Bob[/\?$/] = 'Sure.'
Bob[nil] = Bob[/^$/] = 'Fine. Be that way.'
Bob[/^[^a-z]+$/] = 'Woah, chill out!'
