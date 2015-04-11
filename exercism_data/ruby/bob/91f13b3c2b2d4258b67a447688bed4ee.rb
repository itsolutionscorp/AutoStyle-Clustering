class Bob
  BOB_ANSWERS={
    silence: 'Fine. Be that way!',
    question: 'Sure.',
    yell: 'Whoa, chill out!',
    default: 'Whatever.'
  }

  def question(comment)
    :question if comment.match(/\?\Z/)
  end

  def yell(comment)
    :yell if comment.upcase==comment && comment.match(/[A-Z]/)
  end

  def silence(comment)
    :silence if comment.gsub(/\W/,'').empty?
  end

  def what_did_you_say?(comment)
    silence(comment) || yell(comment) || question(comment) || :default
  end

  def hey(comment)
    BOB_ANSWERS[what_did_you_say?(comment)]
  end
end
