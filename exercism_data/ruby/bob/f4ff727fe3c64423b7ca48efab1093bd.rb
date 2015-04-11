require 'debugger'
class Bob
  YELL_THRESHOLD = 0.3

  def hey(message)
    case
    when yelling?(message)
      "Woah, chill out!"
    when question?(message)
      'Sure.'
    when empty_conversation?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def question?(message)
    message.strip =~ /\?\Z/
  end

  def yelling?(message)
    uppercase_chars = count_matching_chars(message, /[A-Z]/)
    lowercase_chars = count_matching_chars(message, /[a-z]/)
    total_chars     = uppercase_chars + lowercase_chars

    (uppercase_chars.to_f / total_chars) >= YELL_THRESHOLD
  end

  def empty_conversation?(message)
    message.strip.empty?
  end

  #
  ##
  ###
  protected
    def count_matching_chars(message, pattern)
      message.chars.inject(0) do |mem, char|
        mem += 1 if char =~ pattern
        mem
      end
    end
end
