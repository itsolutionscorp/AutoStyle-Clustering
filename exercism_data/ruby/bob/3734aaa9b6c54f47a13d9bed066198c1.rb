# Bob is a lackadaisical teenager. In conversation, his responses are very limited.

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Woah, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying anything.

# He answers 'Whatever.' to anything else
class Bob
  def hey prompt
    if shouting(prompt)
      "Woah, chill out!" 
    elsif asking(prompt)
      "Sure."
    elsif silent(prompt)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def shouting prompt
    prompt == prompt.upcase && (prompt =~ /[a-zA-Z]+/) != nil
  end

  def asking prompt 
    prompt[-1] == "?"
  end

  def silent prompt
    prompt.strip == ""
  end

end
