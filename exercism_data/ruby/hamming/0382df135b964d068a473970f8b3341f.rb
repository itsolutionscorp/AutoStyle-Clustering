class Hamming
  # get the variables and pass them first to truncate
  # second to hamming_diff
  def self.compute(a, b)
  	return 0 if a == b
  	a = truncate(a, b.length)
    b = truncate(b, a.length)
  	hamming_diff(a, b)
  end

  # loop trough each characters of a and compare it with
  # matching index of b.chars
  def self.hamming_diff(a, b)
  	diff = 0
  	a.chars.each_with_index do |a_char, i|
  	  if a_char != b.chars[i]
  	  	diff += 1
  	  end
  	end
  	diff
  end

  # cut strings to a given length
  def self.truncate(text, length)
  	if text
  	  chars = text.chars
  	  (chars.length > length ? chars[0...length] : chars).join
  	end
  end
end
