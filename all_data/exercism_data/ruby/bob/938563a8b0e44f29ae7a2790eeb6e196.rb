module CheckTeenSpeech
  def question?
    end_with? '?'
  end

  def command?
    self == upcase
  end

  def silence?
    strip.empty?
  end
end

# Mix-in the teen speech checks, rather than declare directly.
# This creates a class hierarchy relationship, which allows extending behavior while
# retaining the original behavior.
# Edge case, but since we're just playing around, why not.
class String
  include CheckTeenSpeech
end

# Example of extending one of the check methods, as per above.
# If CheckTeenSpeech methods had been declared directly on String,
# this would be harder. In a codebase we entirely own, this isn't
# too much of an issue, but if we distribute a gem then playing nice
# with extension attempts might be more of an issue.
class String
  def command?
    super || downcase.include?("right now!")
  end
end

module ActsLikeTeen
  def hey(text)
    return "Fine. Be that way!" if !text || text.silence?
    return "Woah, chill out!" if text.command?
    return "Sure." if text.question?

    "Whatever." # Statement.
  end
end

# Should just be an instance, not a subclass.
class Bob
  include ActsLikeTeen
end
