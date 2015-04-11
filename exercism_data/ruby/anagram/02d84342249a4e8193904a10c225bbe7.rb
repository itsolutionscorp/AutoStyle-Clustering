# Solution #2
#   Having stooped to doing something in Anagram's constructor,
#   I was moved to push behavior out of the Anagram class and into an
#   object to which it could send a message.
#
#   Overkill for the simple problem at hand, but I contend that this
#   strategy is a a better real world solution for most real world 
#   problems.
class Anagram
  attr_reader :target

  def initialize(raw_target)
    @target = AnagramWord.new(raw_target)
  end

  def match(possibilities)
    possibilities.select {|possibility| target.anagram?(possibility)}
  end

  class AnagramWord < String

    def initialize(object)
      object = anagramize(object)
      super
    end

    def anagram?(word)
      anagramize(word) == self
    end

    def anagramize(str)
      str.to_s.chars.sort.join
    end

  end
end



# Solution #1
#   Pros: straightforward
#   Cons: target is resorted for every possibility
# class Anagram
#   attr_reader :target
#   def initialize(target)
#     @target = target
#   end
# 
#   def match(possibilities)
#     possibilities.select {|possibility| anagram?(possibility, target)}
#   end
# 
#   def anagram?(word, other_word)
#     as_sorted_array(word) == as_sorted_array(other_word)
#   end
# 
#   def as_sorted_array(str)
#     str.chars.sort
#   end
# end
