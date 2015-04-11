# Bob is a lackadaisical teenager. In conversation, his responses are very limited.

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Whoa, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying
# anything.

# He answers 'Whatever.' to anything else.


class Bob
  def hey(words)
    words = words.gsub(/[0-9]/, "")
    if words =~ /^\s*$/
      'Fine. Be that way!'
    elsif words[-1].chr == '?' && words != words.upcase
      'Sure.'
    elsif words == words.upcase
      'Whoa, chill out!'
    else
      'Whatever.'
    end
  end
end
