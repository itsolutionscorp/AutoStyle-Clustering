class Anagram
  attr_reader :target, :sorted_target_chars
  def initialize(target)
    @target = target.downcase
    @sorted_target_chars = @target.chars.sort
  end

  def match(candidates=[])
    candidates = candidates.map {|c| [c, c.downcase] }
    candidates.delete_if {|c| identical_or_not_anagram(c) }
    candidates.map {|candidate| candidate[0] }
  end

private
  def identical_or_not_anagram(candidate)
    identical(candidate) || not_anagram(candidate)
  end

  def not_anagram(candidate)
    candidate[1].chars.sort != sorted_target_chars
  end

  def identical(candidate)
    candidate[1] == target
  end
end
