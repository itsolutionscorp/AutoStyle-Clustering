class Words
  attr_reader :words

  def initialize(words)
    @words = words.downcase.gsub(/[^a-z0-9 ]/,"")
    # @words = words.downcase.gsub(/[\W]/,"") ##Can't use this one because it also gets the blank spaces. I could move it
  end

  def count
    answer = {}
    words.split(" ").each do |word|
      answer[word] += 1 if answer[word]
      answer[word] = 1 unless answer[word]
    end
    answer
  end
end


# Are you familiar with the `/\W/` regex shorthand character class? Try initializing your hash with a reasonable default value, like `0`.
#              kytrinyx

#If I use /\W/ it breaks because it gets rid of the empty spaces and won't split
#if I put a 0 in the hash it returns too many things in the hash
