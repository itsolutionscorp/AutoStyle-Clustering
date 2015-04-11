class Bob
  def hey(comment)
    return give_answer if is_being_asked_question?(comment)
    return chill_out   if is_being_yelled_at?(comment)
    return annoyed     if is_being_given_silent_treatment?(comment)

    not_interested
  end

  private

  def is_being_yelled_at?(comment)
    return false if comment =~ only_contains_numbers
    return false if is_being_given_silent_treatment?(comment)

    comment.upcase == comment
  end

  def is_being_asked_question?(comment)
    return false if is_being_asked_question_aggressively?(comment)
    return false if is_being_asked_rhetorical_question?(comment)

    comment =~ contains_question_mark 
  end

  def is_being_asked_question_aggressively?(comment)
    comment =~ all_caps_with_question_mark
  end

  def is_being_given_silent_treatment?(comment)
    comment.strip == ""
  end

  def is_being_asked_rhetorical_question?(comment)
    comment != comment.strip
  end

  def contains_question_mark
    /[?]$/
  end

  def all_caps_with_question_mark
    /^[A-Z, ]*[?$]/
  end

  def only_contains_numbers
    /^[0-9,. ]*$/
  end

  def give_answer
    "Sure."
  end

  def chill_out
    "Whoa, chill out!"
  end

  def annoyed
    "Fine. Be that way!"
  end

  def not_interested
    "Whatever."
  end
end
