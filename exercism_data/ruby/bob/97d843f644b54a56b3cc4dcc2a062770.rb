class Bob

  def initialize
  end

  def hey(words)
    if shouting(words)
      'Woah, chill out!'
    elsif words == ''
      'Fine. Be that way.'
    elsif question(words)
      'Sure.'
    elsif statement(words)
      'Whatever.'
    end
  end

private
  def shouting(words)
    words == words.upcase && words != ''
  end

  def statement(words)
    words[1..-1] == words[1..-1].downcase
  end

  def question(words)
    words.end_with?("?")
  end
end
 # You don't need to split the words before checking things: You can upcase or downcase an entire string even if it has spaces.
 #             kytrinyx
 # Are you familiar with `String#end_with?`?
 #             kytrinyx
 # good job. ruby has some built in methods that can make this look a little more clean. for example def question words.end_with?("?") end
 #             blairanderson
