class Bob
  def hey(words)
    char_arr = words.each_char.to_a
    punctuation = char_arr.last

    return "Whatever." if punctuation == "." || (newline_present?(char_arr) && !words.empty?)

    return "Fine. Be that way!" if words.empty? || words.strip.empty?

    if punctuation == "!"
      if numbers?(char_arr) || all_caps?(char_arr)
        say_chill_out
      else
        return "Whatever."
      end
    end

    if punctuation == "?"
      if numbers?(char_arr) || !all_caps?(char_arr)
        return "Sure."
      else
        say_chill_out
      end
    end

    if numbers?(char_arr) && punctuation != "!"
     return "Whatever."
    end

    say_chill_out if all_caps?(char_arr)
  end

  private

  def numbers?(char_arr)
    char_arr.each do |char|
      if char.to_i > 0
        return true
      end
    end
    return false
  end

  def say_chill_out
    return "Woah, chill out!"
  end

  def all_caps?(char_arr)
    char_arr.pop
    char_arr.each do |char|
      if char != char.capitalize
        return false
        exit
      end
    end
  end

  def newline_present?(char_arr)
    char_arr.include?("\n")
  end
end
