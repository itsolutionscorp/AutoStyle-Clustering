class Bob
  def hey(comment)
    case
      when nothing?(comment)
        return "Fine. Be that way!"
      when all_uppercase?(comment)
        return "Woah, chill out!"
      when is_a_question?(comment)
        return "Sure."
      else
        return "Whatever."
    end
  end

  private

  def all_uppercase?(comment)
    return !comment.empty? && !comment.match(/[a-z]/) && comment.match(/[A-Z]/)
  end

  def is_a_question?(comment)
    return comment[-1] == "?"
  end

  def nothing?(comment)
    return comment.empty? || comment.match(/^\s+$/)
  end
end
