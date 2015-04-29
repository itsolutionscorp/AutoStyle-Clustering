class FoodChainSong

  WORDS = [
    { name: "fly" },
    { 
      name: "spider", 
      action: "It wriggled and jiggled and tickled inside her.", 
      desc: "that wriggled and jiggled and tickled inside her",
    },
    { name: "bird", action: "How absurd to swallow a bird!" },
    { name: "cat",  action: "Imagine that, to swallow a cat!" },
    { name: "dog",  action: "What a hog, to swallow a dog!" },
    { name: "goat", action: "Just opened her throat and swallowed a goat!" },
    { name: "cow",  action: "I don't know how she swallowed a cow!" },
    { name: "horse",action: "She's dead, of course!" }
  ]

  private_constant :WORDS

  def verse num
    phrace(num-1).compact.join "\n"
  end

  def sing
    verses 1, 8
  end

  def verses from, to
    (from..to).inject([]) do |res, index|
      res << verse(index)
      res << "" if index == to
      res
    end.join("\n")
  end

  def phrace i
    result = []
    result << first_line(i) 
    result << second_line(i)
    return result << "" if i == (WORDS.size - 1) 
    (i-1).downto(0).each { |j| result << third_line(j) }
    result << end_line
    result << ""
  end

  private

  def first_line index
    str = "I know an old lady who swallowed a "
    str << WORDS[index][:name] << '.'
  end

  def second_line index
    WORDS[index][:action]
  end

  def third_line index
    str = "She swallowed the %s" % WORDS[index+1][:name]
    str << " to catch the "
    str << [WORDS[index][:name], WORDS[index][:desc]].compact.join(" ") 
    str << "."
  end

  def end_line
    "I don't know why she swallowed the fly. Perhaps she'll die."
  end
end
