class Anagram
  def initialize source
    @raw_source = source.downcase
    @source = standard_form @raw_source
  end

  def match targets
    targets.select do |target|
      raw_target = target.downcase
      are_anagrams = @source == standard_form(raw_target)
      are_same = (@raw_source == raw_target)
      are_anagrams.but_not are_same
    end
  end

  def standard_form word
    word.chars.sort
  end
end

class Object
  def but_not obj
    self && !obj
  end
end
