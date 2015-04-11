# Comments to reviewers:
# Firstly, thank you for taking the time to review! I really appreciate the
# help and I feel like I've learned a lot since starting this!

# Per Katrina's request, there are only a few comments; only the ones I would
# normally do in production work. I try to only comment classes


# Bob is a lackadaisical teenager. In conversation, his responses are very
# limited. He is a Code Kata from exercism.io.
class Bob
  def hey(phrase)
    phrase = IntrospectivePhrase.new(phrase)

    if phrase.question?
      return "Sure."
    elsif phrase.silent?
      return "Fine, be that way."
    elsif phrase.leet?
      return phrase.to_leet_s
    elsif phrase.shouting?
      return "Woah, chill out!"
    else
      return "Whatever."
    end
  end
end

# An IntrospectivePhrase is a String that is capable of saying what type
# of sentence it is. This is to avoid monkey-patching String directly
# while still allowing direct method calls.
class IntrospectivePhrase < String
  def question?
    self.strip.end_with?('?')
  end

  def shouting?
    self.upcase == self
  end

  def silent?
    self.empty?
  end

  def leet?
    self.strip.start_with?("Bro,")
  end

  def to_leet_s
    LeetString.new(self.gsub('Bro,', '')).to_s
  end
end

# LeetStrings are Strings that output in l33t sP34k.
class LeetString < String
  def to_s
    return @leet ||= build_leet_string
  end

  def build_leet_string
    return self.dup.build_leet_string!
  end
  def build_leet_string!
    self.strip!
    self.downcase!
    self.gsub!(/[aeio]/, "a" => 4, "e" => 3, "i" => 1, "o" => 0)
    self.alternate_case!

    return self
  end

  # Alternate case between up/downcase for any consecutive sequence of
  # consonants or the letters u or y.
  # Example:
  # "hello".alternate_case #=> "helLo"
  # "bbb4bbb".alternate_case #=> "bBb4bBb"
  # "BBB".alternate_case #=> "BbB"
  def alternate_case
    return self.dup.alternate_case!
  end
  def alternate_case!
    ret = ""
    previous_char = ""

    self.each_char do |char|
      if previous_char =~ /[b-df-hj-np-z]/ # Consonants
        char.upcase!
      end
      ret << char
      previous_char = char
    end

    # Use a destructive method already in String to replace self. We can't
    # just do `self = ret` because of a SyntaxError.
    return self.replace(ret)
  end
end
