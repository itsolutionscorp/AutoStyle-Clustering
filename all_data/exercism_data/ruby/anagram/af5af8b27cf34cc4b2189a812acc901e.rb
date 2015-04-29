class Anagram

  attr_reader :base

  def initialize base
    @base = base
  end

  def match candidates
    candidates.select { |candidate| anagram? candidate.downcase }
  end

  def anagram? candidate
    candidate != base and chars( candidate ) == base_chars
  end

private

  def base_chars
    @base_chars ||= chars( base.downcase )
  end

  def chars word
    word.chars.sort
  end

end
